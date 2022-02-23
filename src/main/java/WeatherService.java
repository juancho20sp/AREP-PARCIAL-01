import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeatherService {
    public static String getHomePage() {
//        return  "<!DOCTYPE html>"
//                + "<head>\n"
//                +"    <title>¡Vamos por el clima!</title>\n" +
//                "</head>\n" +
//                "\n" +
//                "<body>\n" +
//                "    <h1>ECI Clima</h1>\n" +
//                "    <h3>Juan David Murillo</h3>\n" +
//                "\n" +
//                "    <form>\n" +
//                "        <label for=\"cityInput\">Ingrese el nombre de la ciudad a buscar: </label>\n" +
//                "        <input type=\"text\" id=\"cityInput\">\n" +
//                "        <button id=\"formButton\">Consultar clima</button>\n" +
//                "    </form>\n" +
//                "\n" +
//                "    <script>\n" +
//                "        const input = document.querySelector('#cityInput');\n" +
//                "        const button = document.querySelector('#formButton');\n" +
//                "\n" +
//                "        const fetchData = async (event) => {\n" +
//                "            event.preventDefault();\n" +
//                "            console.log(input.value);\n" +
//                "            const city = input.value;\n" +
//                "\n" +
//                "            if (city.length === 0) {\n" +
//                "                return;\n" +
//                "            }\n" +
//                "\n" +
//                "            // DEV URL\n" +
//                "            const URL = `http://localhost:1234/consulta?ciudad=${city}`\n" +
//                "            //const URL = `http://localhost:1234/clima`\n" +
//                "\n" +
//                "\n" +
//                "            // $\n" +
//                "            console.log(`Fetching: ${URL}`);\n" +
//                "\n" +
//                "\n" +
//                "            const response = await fetch(URL, {\n" +
//                "                method: 'GET',\n" +
//                "                headers: {\n" +
//                "                    'Content-Type': 'application/json'\n" +
//                "                    // 'Content-Type': 'text/html'\n" +
//                "                }\n" +
//                "            });\n" +
//                "\n" +
//                "            const data = await response.json();\n" +
//                "\n" +
//                "            console.log(data);\n" +
//                "        }\n" +
//                "\n" +
//                "        button.addEventListener('click', fetchData);\n" +
//                "    </script>\n" +
//                "\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>";
        return "<!DOCTYPE html>\n" +
                "\n" +
                "<head>\n" +
                "    <title>¡Vamos por el clima!</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <h1>ECI Clima</h1>\n" +
                "    <h3>Juan David Murillo</h3>\n" +
                "\n" +
                "    <form>\n" +
                "        <label for=\"cityInput\">Ingrese el nombre de la ciudad a buscar: </label>\n" +
                "        <input type=\"text\" id=\"cityInput\">\n" +
                "        <button id=\"formButton\">Consultar clima</button>\n" +
                "        <code>\n" +
                "            <pre id=\"code\"></pre>\n" +
                "        </code>\n" +
                "    </form>\n" +
                "\n" +
                "    <script>\n" +
                "        const input = document.querySelector('#cityInput');\n" +
                "        const button = document.querySelector('#formButton');\n" +
                "        const preCode = document.querySelector('#code');\n" +
                "\n" +
                "        const fetchData = async (event) => {\n" +
                "            event.preventDefault();\n" +
                "            console.log(input.value);\n" +
                "            const city = input.value;\n" +
                "\n" +
                "            if (city.length === 0) {\n" +
                "                return;\n" +
                "            }\n" +
                "\n" +
                "            // DEV URL\n" +
                "            const URL = `http://localhost:1234/consulta?ciudad=${city}`\n" +
                "            //const URL = `http://localhost:1234/clima`\n" +
                "\n" +
                "\n" +
                "            // $\n" +
                "            console.log(`Fetching: ${URL}`);\n" +
                "\n" +
                "\n" +
                "            const response = await fetch(URL, {\n" +
                "                method: 'GET',\n" +
                "                headers: {\n" +
                "                    'Content-Type': 'application/json'\n" +
                "                    // 'Content-Type': 'text/html'\n" +
                "                }\n" +
                "            });\n" +
                "\n" +
                "            const data = await response.json();\n" +
                "\n" +
                "            preCode.textContent = JSON.stringify(data, undefined, 2);\n" +
                "            console.log(data);\n" +
                "        }\n" +
                "\n" +
                "        button.addEventListener('click', fetchData);\n" +
                "    </script>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getWeatherData(String param) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ param + "&appid" +
                "=c0dc242e7bc4730571646bf93e841ad8";

        String htmlResponse = "";

        try {
            // Create the URL
            URL siteURL = new URL(url);

            // Create the connection
            URLConnection urlConnection = siteURL.openConnection();

            // Get headers
            Map<String, List<String>> headers = urlConnection.getHeaderFields();

            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

            // Print headers
            for(Map.Entry<String, List<String>> entry : entrySet){
                String headerName = entry.getKey();

                if (headerName != null) {
                    System.out.println(headerName + ": ");
                }

                List<String> headerValues = entry.getValue();

                for(String value: headerValues) {
                    System.out.println(value);
                }

                System.out.println(" ");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()));

            String inputLine = null;

            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                htmlResponse += inputLine;
            }

        } catch (MalformedURLException ex) {
            System.out.println("URL mal formada");
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

        // $
        System.out.println("html response: " + htmlResponse);

        return htmlResponse;
    }


}
