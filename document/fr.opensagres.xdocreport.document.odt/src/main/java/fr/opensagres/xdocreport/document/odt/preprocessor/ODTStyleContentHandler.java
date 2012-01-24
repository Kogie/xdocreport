package fr.opensagres.xdocreport.document.odt.preprocessor;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import fr.opensagres.xdocreport.document.odt.ODTConstants;
import fr.opensagres.xdocreport.document.preprocessor.sax.BufferedDocument;
import fr.opensagres.xdocreport.document.preprocessor.sax.BufferedDocumentContentHandler;
import fr.opensagres.xdocreport.document.preprocessor.sax.IBufferedRegion;

public class ODTStyleContentHandler extends
        BufferedDocumentContentHandler<BufferedDocument> implements ODTConstants {

    protected List<Integer> existingStyles = new ArrayList<Integer>();

    protected static String[] HEADING_STYLES= new String[]{
                "<style:style style:name=\"Heading_20_1\" style:display-name=\"Heading 1\" style:family=\"paragraph\" style:parent-style-name=\"Heading\" style:next-style-name=\"Text_20_body\" style:default-outline-level=\"1\" style:class=\"text\"><style:text-properties fo:font-size=\"115%\" fo:font-weight=\"bold\" style:font-size-asian=\"115%\" style:font-weight-asian=\"bold\" style:font-size-complex=\"115%\" style:font-weight-complex=\"bold\"/></style:style>",
                "<style:style style:name=\"Heading_20_2\" style:display-name=\"Heading 2\" style:family=\"paragraph\" style:parent-style-name=\"Heading\" style:next-style-name=\"Text_20_body\" style:default-outline-level=\"2\" style:class=\"text\"><style:text-properties fo:font-size=\"14pt\" fo:font-style=\"italic\" fo:font-weight=\"bold\" style:font-size-asian=\"14pt\" style:font-style-asian=\"italic\" style:font-weight-asian=\"bold\" style:font-size-complex=\"14pt\" style:font-style-complex=\"italic\" style:font-weight-complex=\"bold\"/></style:style>",
                "<style:style style:name=\"Heading_20_3\" style:display-name=\"Heading 3\" style:family=\"paragraph\" style:parent-style-name=\"Heading\" style:next-style-name=\"Text_20_body\" style:default-outline-level=\"3\" style:class=\"text\"><style:text-properties fo:font-size=\"14pt\" fo:font-weight=\"bold\" style:font-size-asian=\"14pt\" style:font-weight-asian=\"bold\" style:font-size-complex=\"14pt\" style:font-weight-complex=\"bold\"/></style:style>",
                "<style:style style:name=\"Heading_20_4\" style:display-name=\"Heading 4\" style:family=\"paragraph\" style:parent-style-name=\"Heading\" style:next-style-name=\"Text_20_body\" style:default-outline-level=\"4\" style:class=\"text\"><style:text-properties fo:font-size=\"85%\" fo:font-style=\"italic\" fo:font-weight=\"bold\" style:font-size-asian=\"85%\" style:font-style-asian=\"italic\" style:font-weight-asian=\"bold\" style:font-size-complex=\"85%\" style:font-style-complex=\"italic\" style:font-weight-complex=\"bold\"/></style:style>",
                "<style:style style:name=\"Heading_20_5\" style:display-name=\"Heading 5\" style:family=\"paragraph\" style:parent-style-name=\"Heading\" style:next-style-name=\"Text_20_body\" style:default-outline-level=\"5\" style:class=\"text\"><style:text-properties fo:font-size=\"85%\" fo:font-weight=\"bold\" style:font-size-asian=\"85%\" style:font-weight-asian=\"bold\" style:font-size-complex=\"85%\" style:font-weight-complex=\"bold\"/></style:style>",
                "<style:style style:name=\"Heading_20_6\" style:display-name=\"Heading 6\" style:family=\"paragraph\" style:parent-style-name=\"Heading\" style:next-style-name=\"Text_20_body\" style:default-outline-level=\"6\" style:class=\"text\"><style:text-properties fo:font-size=\"75%\" fo:font-weight=\"bold\" style:font-size-asian=\"75%\" style:font-weight-asian=\"bold\" style:font-size-complex=\"75%\" style:font-weight-complex=\"bold\"/></style:style>"
    };

    @Override
    public boolean doStartElement(String uri, String localName, String name,
            Attributes attributes) throws SAXException {

        if ("style".equals(localName)) {
            String styleName = attributes.getValue("style:name");
            if (styleName!=null && styleName.startsWith("Heading_20_")) {
                String headerIdx = styleName.substring(11);
                Integer idx = Integer.parseInt(headerIdx);
                existingStyles.add(idx);
            }
        }
        return super.doStartElement(uri, localName, name, attributes);
    }

    @Override
    public void doEndElement(String uri, String localName, String name)
            throws SAXException {
        if ("styles".equals(localName)) {
            for (int i = 1; i <=6; i++) {
                if (!existingStyles.contains(i)) {
                    generateHeaderStyle(i);
                }
            }
        }
        super.doEndElement(uri, localName, name);
    }

    protected void generateHeaderStyle(int level) {
        IBufferedRegion region = getCurrentElement();
        region.append(HEADING_STYLES[level-1]);
    }

}
