import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class WebServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            System.out.println("Server started!");

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter out = new PrintWriter(socket.getOutputStream())) {

                    while (in.ready()) {
                        System.out.println(in.readLine());
                    }

                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html; charset=utf-8");
                    out.println();
                    //out.println("<p><span style=\"color: #c82828;\">Привет всем!</p>");
                    out.println("<html>\n" +
                            " <head>\n" +
                            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                            "  <title>Цвет фона</title>\n" +
                            " </head>\n" +
                            " <body bgcolor=\"#ffcc00\">\n" +
                            "   <p style=\"text-align:center\"><span style=\"font-family:Verdana,Geneva,sans-serif\"><span style=\"font-size:36px\"><strong><img alt=\"heart\" src=\"https://sdelatlending.ru/wp-content/themes/xmarkup/ckeditor/plugins/smiley/images/heart.png\" style=\"height:23px; width:23px\" title=\"heart\" /><span style=\"background-color:#ffcc00\">Hi there!</span><img alt=\"heart\" src=\"https://sdelatlending.ru/wp-content/themes/xmarkup/ckeditor/plugins/smiley/images/heart.png\" style=\"height:23px; width:23px\" title=\"heart\" /></strong></span></span></p>\n" +
                            "\n" +
                            "<p style=\"text-align:center\"><span style=\"font-family:Verdana,Geneva,sans-serif\"><span style=\"font-size:36px\"><strong><span style=\"background-color:#ffcc00\">I`m simple Web Server&nbsp;</span></strong></span></span></p>\n" +
                            "\n" +
                            "<p style=\"text-align:center\"><span style=\"font-family:Verdana,Geneva,sans-serif\"><span style=\"font-size:36px\"><strong><img alt=\"smiley\" src=\"https://sdelatlending.ru/wp-content/themes/xmarkup/ckeditor/plugins/smiley/images/regular_smile.png\" style=\"height:23px; width:23px\" title=\"smiley\" /></strong></span></span></p>\n" +
                            " </body>\n" +
                            "</html>\n");

                    out.flush();

                    System.out.println("Client disconnected!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
