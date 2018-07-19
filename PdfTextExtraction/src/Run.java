//import org.apache.pdfbox.pdmodel.PDDocument;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.stream.IntStream;
//
//public class Run {
//
//    public static void main(String[] args) throws IOException {
//        PDDocument document = null;
//        try {
//            document = PDDocument.load(new File("C:\\Users\\Lenovo\\Desktop\\ImageToText\\PdfTextExtraction\\QF - LMV Owner Driver.pdf"));
//            PdfText strip = new PdfText(document);
//            strip.setSortByPosition(true);
//
//            IntStream.range(0, document.getNumberOfPages())
//                    .forEach(number -> {
//                                strip.stripPage(number, PdfText.Transformations.Flip);
//                                if (strip.PageLines.isPresent()) {
//                                    strip.PageLines.get()
//                                            .stream()
//                                            .filter(line -> !line.isEmpty())
//                                            .forEach(line -> System.out.println(line));
//                                } else {
//                                    System.out.println("error on page "+ number);
//                                }
//                            }
//                    );
//        } catch (FileNotFoundException e) {
//            System.out.println("no file");
//        } catch (IOException e) {
//            System.out.println("bad file");
//        }
//        finally {
//            if (document != null) {
//                document.close();
//            }
//        }
//    }
//}