package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;
    private static final Map<Character, String> mapaDeSustitucionCifrado  = new HashMap<>();

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = buffReader.readLine();
            clientHandlers.add(this);
            String nombre = Cifrar(mapaDeSustitucionCifrado, name);
            String bienvenida = "1IC1P0L0X0M0N1K2O200  " + nombre + "  00Z1O200C1M0K2P0O2I1X000O2E000J1Z1O2K2";
            broadcastMessage(bienvenida);
        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = buffReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.name.equals(name)) {
                    clientHandler.buffWriter.write(messageToSend);
                    clientHandler.buffWriter.newLine();
                    clientHandler.buffWriter.flush();
                }
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        String nombre = Cifrar(mapaDeSustitucionCifrado, name);
        broadcastMessage("1IC1P0L0X0M0N1K2O200 " + nombre + " 00L0C100Z1O200N1I1X000I1C1E000J1Z1O2K2");
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
        removeClientHandler();
        try {
            if (buffReader != null) {
                buffReader.close();
            }
            if (buffWriter != null) {
                buffWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void crearMapas(){
        mapaDeSustitucionCifrado.put('a', "O2");
        mapaDeSustitucionCifrado.put('A', "2T");
        mapaDeSustitucionCifrado.put('b', "G1");
        mapaDeSustitucionCifrado.put('B', "2G");
        mapaDeSustitucionCifrado.put('c', "J1");
        mapaDeSustitucionCifrado.put('d', "I1");
        mapaDeSustitucionCifrado.put('e', "C1");
        mapaDeSustitucionCifrado.put('f', "A1");
        mapaDeSustitucionCifrado.put('g', "S1");
        mapaDeSustitucionCifrado.put('h', "Z1");
        mapaDeSustitucionCifrado.put('i', "N1");
        mapaDeSustitucionCifrado.put('j', "F1");
        mapaDeSustitucionCifrado.put('k', "T1");
        mapaDeSustitucionCifrado.put('l', "E0");
        mapaDeSustitucionCifrado.put('m', "W0");
        mapaDeSustitucionCifrado.put('n', "M0");
        mapaDeSustitucionCifrado.put('ñ', "V0");
        mapaDeSustitucionCifrado.put('o', "X0");
        mapaDeSustitucionCifrado.put('p', "H0");
        mapaDeSustitucionCifrado.put('q', "D0");
        mapaDeSustitucionCifrado.put('r', "P0");
        mapaDeSustitucionCifrado.put('s', "L0");
        mapaDeSustitucionCifrado.put('t', "K2");
        mapaDeSustitucionCifrado.put('u', "R2");
        mapaDeSustitucionCifrado.put('v', "Y2");
        mapaDeSustitucionCifrado.put('w', "U2");
        mapaDeSustitucionCifrado.put('x', "Q2");
        mapaDeSustitucionCifrado.put('y', "B2");
        mapaDeSustitucionCifrado.put('z', "J2");
        mapaDeSustitucionCifrado.put('C', "2S");
        mapaDeSustitucionCifrado.put('D', "2Y");
        mapaDeSustitucionCifrado.put('E', "2B");
        mapaDeSustitucionCifrado.put('F', "2R");
        mapaDeSustitucionCifrado.put('G', "2I");
        mapaDeSustitucionCifrado.put('H', "2Q");
        mapaDeSustitucionCifrado.put('I', "1q");
        mapaDeSustitucionCifrado.put('J', "1X");
        mapaDeSustitucionCifrado.put('K', "1Z");
        mapaDeSustitucionCifrado.put('L', "1G");
        mapaDeSustitucionCifrado.put('M', "1O");
        mapaDeSustitucionCifrado.put('N', "1Y");
        mapaDeSustitucionCifrado.put('Ñ', "1S");
        mapaDeSustitucionCifrado.put('O', "1J");
        mapaDeSustitucionCifrado.put('P', "1I");
        mapaDeSustitucionCifrado.put('Q', "1Q");
        mapaDeSustitucionCifrado.put('R', "0q");
        mapaDeSustitucionCifrado.put('S', "0W");
        mapaDeSustitucionCifrado.put('T', "0P");
        mapaDeSustitucionCifrado.put('U', "0G");
        mapaDeSustitucionCifrado.put('V', "0S");
        mapaDeSustitucionCifrado.put('W', "0E");
        mapaDeSustitucionCifrado.put('X', "0B");
        mapaDeSustitucionCifrado.put('Y', "0C");
        mapaDeSustitucionCifrado.put('Z', "0I");
        mapaDeSustitucionCifrado.put('1', "Sz");
        mapaDeSustitucionCifrado.put('2', "Ay");
        mapaDeSustitucionCifrado.put('3', "Fx");
        mapaDeSustitucionCifrado.put('4', "Ew");
        mapaDeSustitucionCifrado.put('5', "iF");
        mapaDeSustitucionCifrado.put('6', "Te");
        mapaDeSustitucionCifrado.put('7', "Od");
        mapaDeSustitucionCifrado.put('8', "Lc");
        mapaDeSustitucionCifrado.put('9', "Ub");
        mapaDeSustitucionCifrado.put('0', "Da");
        mapaDeSustitucionCifrado.put(' ', "00");
        mapaDeSustitucionCifrado.put(':', "01");
        mapaDeSustitucionCifrado.put('!', "02");
        mapaDeSustitucionCifrado.put('$', "03");
        mapaDeSustitucionCifrado.put('#', "04");
        mapaDeSustitucionCifrado.put('*', "05");
        mapaDeSustitucionCifrado.put('%', "06");
        mapaDeSustitucionCifrado.put('&', "07");
        mapaDeSustitucionCifrado.put('/', "08");
        mapaDeSustitucionCifrado.put('(', "09");
        mapaDeSustitucionCifrado.put(')', "10");
        mapaDeSustitucionCifrado.put('=', "11");
        mapaDeSustitucionCifrado.put('¿', "12");
        mapaDeSustitucionCifrado.put('¡', "13");
        mapaDeSustitucionCifrado.put('?', "14");
        mapaDeSustitucionCifrado.put(';', "15");
        mapaDeSustitucionCifrado.put('<', "16");
        mapaDeSustitucionCifrado.put('>', "17");
        mapaDeSustitucionCifrado.put('-', "18");
        mapaDeSustitucionCifrado.put('_', "19");
        mapaDeSustitucionCifrado.put('+', "20");
        mapaDeSustitucionCifrado.put('~', "21");
        mapaDeSustitucionCifrado.put('^', "22");
        mapaDeSustitucionCifrado.put('á', "aA");
        mapaDeSustitucionCifrado.put('é', "eE");
        mapaDeSustitucionCifrado.put('í', "iI");
        mapaDeSustitucionCifrado.put('ó', "oO");
        mapaDeSustitucionCifrado.put('ú', "uU");
        mapaDeSustitucionCifrado.put('Á', "Aa");
        mapaDeSustitucionCifrado.put('É', "Ee");
        mapaDeSustitucionCifrado.put('Í', "Ii");
        mapaDeSustitucionCifrado.put('Ó', "Oo");
        mapaDeSustitucionCifrado.put('Ú', "Uu");
        mapaDeSustitucionCifrado.put('ä', "aa");
        mapaDeSustitucionCifrado.put('ë', "ee");
        mapaDeSustitucionCifrado.put('ï', "ii");
        mapaDeSustitucionCifrado.put('ö', "oo");
        mapaDeSustitucionCifrado.put('ü', "uu");
        mapaDeSustitucionCifrado.put('Ä', "AA");
        mapaDeSustitucionCifrado.put('Ë', "EE");
        mapaDeSustitucionCifrado.put('Ï', "II");
        mapaDeSustitucionCifrado.put('Ö', "OO");
        mapaDeSustitucionCifrado.put('Ü', "UU");
        mapaDeSustitucionCifrado.put('\'', "24");
        mapaDeSustitucionCifrado.put('{', "25");
        mapaDeSustitucionCifrado.put('}', "26");
        mapaDeSustitucionCifrado.put('[', "27");
        mapaDeSustitucionCifrado.put(']', "28");
        mapaDeSustitucionCifrado.put('\"', "29");
        mapaDeSustitucionCifrado.put('\\', "30");
        mapaDeSustitucionCifrado.put('.', "31");
        mapaDeSustitucionCifrado.put(',', "32");
    }

    private static String Cifrar(Map<Character, String> mapa, String mensaje) {
        StringBuilder mensajeCifrado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            if (mapa.containsKey(caracter)) {
                mensajeCifrado.append(mapa.get(caracter));
            } else {
                mensajeCifrado.append(caracter);
            }
        }
        return mensajeCifrado.toString();
    }

}
