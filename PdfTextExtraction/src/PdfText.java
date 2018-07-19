//
//    // based on code by Ben Litchfield and Tilman Hausherr
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.pdfbox.text.TextPosition;
//
//import java.awt.geom.AffineTransform;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//    public class PdfText extends PDFTextStripper {
//        private final PDDocument document;
//        public Optional<List<String>> PageLines;
//
//        public PdfText(PDDocument document) throws IOException {
//            this.document = document;
//        }
//
//        public enum Transformations {
//            Flip,
//            Rotate
//        }
//
//        private PDPage flipPage (PDPage page) {
//            AffineTransform at = new AffineTransform();
//            at.translate(0, page.getBBox().getHeight());
//            at.scale(1, -1);
//            return page;
//        }
//
//        private PDPage rotatePage (PDPage page) {
//            AffineTransform at = new AffineTransform();
//            int rotation = page.getRotation();
//            if (rotation != 0) {
//                PDRectangle mediaBox = page.getMediaBox();
//                switch (rotation) {
//                    case 90:
//                        at.translate(mediaBox.getHeight(), 0);
//                        break;
//                    case 270:
//                        at.translate(0, mediaBox.getWidth());
//                        break;
//                    case 180:
//                        at.translate(mediaBox.getWidth(), mediaBox.getHeight());
//                        break;
//                    default:
//                        break;
//                }
//                at.rotate(Math.toRadians(rotation));
//            }
//            return page;
//        }
//
//
//        public void stripPage(int pageNumber, Transformations... transformations) {
//
//            PDPage page = document.getPage(pageNumber);
//            for (Transformations transformation : transformations) {
//                if (transformation == Transformations.Flip) {
//                    flipPage(page);
//                }
//                if (transformation == Transformations.Rotate) {
//                    rotatePage(page);
//                }
//            }
//
//            ++pageNumber;
//            setStartPage(pageNumber);
//            setEndPage(pageNumber);
//            PageLines = Optional.of(new ArrayList<>());
//
//            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
//            try {
//                writeText(document, dummy);
//            } catch (IOException e) {
//                PageLines.empty();
//            }
//        }
//
//        @Override
//        protected void writeString(String string, List<TextPosition> textPositions) {
//            if (PageLines.isPresent()) {
//                String result = "";
//                for (TextPosition text : textPositions) {
//                    result += text.getUnicode();
//                }
//                PageLines.get().add(result.trim());
//            }
//        }
//
//    }
//
