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
package fr.opensagres.xdocreport.template.textstyling;

/**
 * Handler to build a document.
 *
 */
public interface IDocumentHandler {

    /**
     * Start the document.
     */
    void startDocument();

    /**
     * End the document.
     */
    void endDocument();

    /**
     * Start paragraph.
     */
    void startParagraph();

    /**
     * End paragraph.
     */
    void endParagraph();

    /**
     * Start bold style.
     */
    void startBold();

    /**
     * End bold style.
     */
    void endBold();

    /**
     * Start italics style.
     */
    void startItalics();

    /**
     * End italics style.
     */
    void endItalics();

    /**
     * Start ordered list.
     */
    void startOrderedList();

    /**
     * End ordered list.
     */
    void endOrderedList();

    /**
     * Start unordered list.
     */
    void startUnorderedList();

    /**
     * End unordered list.
     */
    void endUnorderedList();

    /**
     * Start list item.
     */
    void startListItem();

    /**
     * End list item.
     */
    void endListItem();

    /**
     * Text content.
     *
     * @param s
     */
    void handleString(String s);

    void startHeading(int level);

    void endHeading(int level);

    //
    // void endCaption();
    // void endDocument();
    // void endIndent();
    // void endItalics();
    // void endLiteral();
    // void endNormalLinkWithCaption();
    // void endOrderedList();
    // void endOrderedListItem();
    // void endParagraph();
    // void endPre();
    // void endSmartLinkWithCaption();
    // void endTable();
    // void endTableData();
    // void endTableHeader();
    // void endTableRecord();
    // void endUnorderedList();
    // void endUnorderedListItem();
    // void handleNormalLinkWithoutCaption(String string);
    // void handleNowiki(String nowiki);
    // void handleSmartLinkWithoutCaption(String string);
    // void handleString(String s);
    //
    // void startCaption(AttributeList captionOptions);
    // void startDocument();
    // void startIndent();
    // void startItalics();
    // void startLiteral();
    // void startNormalLinkWithCaption(String s);
    // void startOrderedList();
    // void startOrderedListItem();
    // void startParagraph();
    // void startPre();
    // void startSmartLinkWithCaption(String s);
    // void startTable(AttributeList tableOptions);
    // void startTableData(AttributeList options);
    // void startTableHeader(AttributeList list);
    // void startTableRecord(AttributeList rowOptions);
    // void startUnorderedList();
    // void startUnorderedListItem();

}
