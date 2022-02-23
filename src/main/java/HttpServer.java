import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {
    private static HttpServer _instance = new HttpServer();

    private HttpServer() { }

    private static HttpServer getInstance() {
        return _instance;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }

        return 1234;
    }

    public void processClientResponse(Socket clientSocket) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));

            String inputLine;
            String path = "";
            String param = "";
            String method = "";

            ArrayList<String> headers = new ArrayList<>();

            while((inputLine = in.readLine()) != null) {
                if (method.isEmpty()) {
                    try {
                        String[] requestString = inputLine.split(" ");

                        // $
                        System.out.println("Request string: " + inputLine );

                        String[] res = requestString[1].split("\\?");
                        method = requestString[0];
                        path = res[0];

                        if (path.equals("/clima")) {
                            System.out.println("ENTRAMOS A CLIMA");
//                            out.println(createResponse(path, null));
                        }

                        if (path.equals("/consulta")){
                            param = res[1].split("=")[1];
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    headers.add(inputLine);
                }

                // $
//                System.out.println("Method: " + method);
//                System.out.println("Path: " + path);
//                System.out.println("Param: " + param);

                if (!in.ready()) {
                    break;
                }
            }

            out.println(createResponse(path, param));

            out.close();

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createResponse(String path, String param) {
        System.out.println("   ");
        System.out.println("Param: " + param);
        System.out.println("   ");

        if (path.equals("/clima")) {
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: "+"text/html"+"\r\n"
                    + "\r\n" + WeatherService.getHomePage();
        } else if (path.equals("/consulta")) {
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: "+"application/json"+"\r\n"
                    + "\r\n"+ WeatherService.getWeatherData(param);
        } else {
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: "+"text/html"+"\r\n"
                    + "\r\n"
                    + "<a href='http://localhost:1234/clima'>Vamos al home page!</a>";
        }
    }

    public void startServer() {
        int port = getPort();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Error listening on port: " + port);
            e.printStackTrace();
        }

        Socket clientSocket = null;
        boolean running = true;

        while (running) {
            try {
                System.out.println("Server listening on port: " + port);
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            processClientResponse(clientSocket);

            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpServer.getInstance().startServer();
    }
}
