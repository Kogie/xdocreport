/**
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com> and Pascal Leclercq <pascal.leclercq@gmail.com>
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.opensagres.xdocreport.document.odt.preprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import fr.opensagres.xdocreport.document.odt.textstyling.IODTStylesGenerator;
import fr.opensagres.xdocreport.document.odt.textstyling.ODTStylesGeneratorFactory;
import fr.opensagres.xdocreport.document.preprocessor.sax.IBufferedRegion;
import fr.opensagres.xdocreport.document.preprocessor.sax.TransformedBufferedDocumentContentHandler;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.IDocumentFormatter;

/**
 * Preprocess styles.xml in order to add global styles (also manages mergeFields extension).
 * 
 * @author <a href="mailto:tdelprat@nuxeo.com">Tiry</a>
 */
public class ODTStyleContentHandler
    extends ODTBufferedDocumentContentHandler
{

    protected List<Integer> existingStyles = new ArrayList<Integer>();

    protected final IODTStylesGenerator styleGen;

    public ODTStyleContentHandler( FieldsMetadata fieldsMetadata, IDocumentFormatter formatter,
                                      Map<String, Object> sharedContext )
    {
        super( fieldsMetadata, formatter, sharedContext );
        styleGen = ODTStylesGeneratorFactory.getStyleGenerator();
    }
    
    @Override
    protected boolean needToProcessAutomaticStyles() {
        return false;
    }

    @Override
    public boolean doStartElement( String uri, String localName, String name, Attributes attributes )
        throws SAXException
    {
        if ( "style".equals( localName ) )
        {
            String styleName = attributes.getValue( "style:name" );
            int level = styleGen.getHeaderStyleNameLevel( styleName );
            if ( level > 0 )
            {
                existingStyles.add( level );
            }
        }
        return super.doStartElement( uri, localName, name, attributes );
    }

    @Override
    public void doEndElement( String uri, String localName, String name )
        throws SAXException
    {
        if ( "styles".equals( localName ) )
        {
            for ( int i = 1; i <= styleGen.getHeaderStylesCount(); i++ )
            {
                if ( !existingStyles.contains( i ) )
                {
                    generateHeaderStyle( i );
                }
            }
        }
        super.doEndElement( uri, localName, name );
    }

    protected void generateHeaderStyle( int level )
    {
        IBufferedRegion region = getCurrentElement();
        region.append( styleGen.generateHeaderStyle( level ) );
    }

    @Override
    protected String getTableRowName()
    {
        return "table:table-row";
    }

    @Override
    protected String getTableCellName()
    {
        return "table:table-cell";
    }

    @Override
    protected ODTBufferedDocument createDocument()
    {
        return new ODTBufferedDocument();
    }

}