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

package fr.opensagres.xdocreport.remoting.resources.services.rest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.activation.DataHandler;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import fr.opensagres.xdocreport.remoting.resources.domain.BinaryData;

/**
 * {@link MessageBodyReader} used by JAXRS to read the {@link BinaryData} from an Http request
 *
 * @author <a href="mailto:tdelprat@nuxeo.com">Tiry</a>
 */
@Provider
public class BinaryDataMessageBodyReader
    implements MessageBodyReader<BinaryData>
{

    public boolean isReadable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType )
    {
        return BinaryData.class.isAssignableFrom( type );
    }

    public BinaryData readFrom( Class<BinaryData> type, Type genericType, Annotation[] annotations,
                                MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
                                InputStream entityStream )
        throws IOException, WebApplicationException
    {

        String filename = "";
        String cd = httpHeaders.getFirst( "Content-Disposition" );
        if ( cd != null )
        {
            filename = cd.replace( "attachement;filename=", "" );
        }

        String mimetype = httpHeaders.getFirst( "Content-Type" );

        String resourceId = httpHeaders.getFirst( "X-resourceId" );

      //  byte[] content=IOUtils.toByteArray(entityStream);
        //BinaryData data = new BinaryData( entityStream, filename, mimetype );
        BinaryData data = new BinaryData( );
        DataHandler dataHandler= new DataHandler(entityStream, mimetype);
        data.setDataHandler(dataHandler);
        //data.getContent().equals(obj)(entityStream);
        data.setFileName(filename);
        data.setMimeType(mimetype);
        data.setResourceId( resourceId );

        return data;
    }
}
