package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public final class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = buffReader.readLine();
            clientHandlers.add(this);
            String nombre = Cifrado(name);
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
        String nombre = Cifrado(name);
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

    private String Cifrado(String name) {
        String mnsjN = "";

        for (int i = 0; i < name.length(); i++) {

            String c = "";

            if (name.charAt(i) == 'a') {
                c = "O2";
            } else if (name.charAt(i) == 'A') {
                c = "2T";
            } else if (name.charAt(i) == 'b') {
                c = "G1";
            } else if (name.charAt(i) == 'B') {
                c = "2G";
            } else if (name.charAt(i) == 'c') {
                c = "J1";
            } else if (name.charAt(i) == 'C') {
                c = "2S";
            } else if (name.charAt(i) == 'd') {
                c = "I1";
            } else if (name.charAt(i) == 'D') {
                c = "2Y";
            } else if (name.charAt(i) == 'e') {
                c = "C1";
            } else if (name.charAt(i) == 'E') {
                c = "2B";
            } else if (name.charAt(i) == 'f') {
                c = "A1";
            } else if (name.charAt(i) == 'F') {
                c = "2R";
            } else if (name.charAt(i) == 'g') {
                c = "S1";
            } else if (name.charAt(i) == 'G') {
                c = "2I";
            } else if (name.charAt(i) == 'h') {
                c = "Z1";
            } else if (name.charAt(i) == 'H') {
                c = "2Q";
            } else if (name.charAt(i) == 'i') {
                c = "N1";
            } else if (name.charAt(i) == 'I') {
                c = "1q";
            } else if (name.charAt(i) == 'j') {
                c = "F1";
            } else if (name.charAt(i) == 'J') {
                c = "1X";
            } else if (name.charAt(i) == 'k') {
                c = "T1";
            } else if (name.charAt(i) == 'K') {
                c = "1Z";
            } else if (name.charAt(i) == 'l') {
                c = "E0";
            } else if (name.charAt(i) == 'L') {
                c = "1G";
            } else if (name.charAt(i) == 'm') {
                c = "W0";
            } else if (name.charAt(i) == 'M') {
                c = "1O";
            } else if (name.charAt(i) == 'n') {
                c = "M0";
            } else if (name.charAt(i) == 'N') {
                c = "1Y";
            } else if (name.charAt(i) == 'ñ') {
                c = "V0";
            } else if (name.charAt(i) == 'Ñ') {
                c = "1S";
            } else if (name.charAt(i) == 'o') {
                c = "X0";
            } else if (name.charAt(i) == 'O') {
                c = "1J";
            } else if (name.charAt(i) == 'p') {
                c = "H0";
            } else if (name.charAt(i) == 'P') {
                c = "1I";
            } else if (name.charAt(i) == 'q') {
                c = "D0";
            } else if (name.charAt(i) == 'Q') {
                c = "1Q";
            } else if (name.charAt(i) == 'r') {
                c = "P0";
            } else if (name.charAt(i) == 'R') {
                c = "0q";
            } else if (name.charAt(i) == 's') {
                c = "L0";
            } else if (name.charAt(i) == 'S') {
                c = "0W";
            } else if (name.charAt(i) == 't') {
                c = "K2";
            } else if (name.charAt(i) == 'T') {
                c = "0P";
            } else if (name.charAt(i) == 'u') {
                c = "R2";
            } else if (name.charAt(i) == 'U') {
                c = "0G";
            } else if (name.charAt(i) == 'v') {
                c = "Y2";
            } else if (name.charAt(i) == 'V') {
                c = "0S";
            } else if (name.charAt(i) == 'w') {
                c = "U2";
            } else if (name.charAt(i) == 'W') {
                c = "0E";
            } else if (name.charAt(i) == 'x') {
                c = "Q2";
            } else if (name.charAt(i) == 'X') {
                c = "0B";
            } else if (name.charAt(i) == 'y') {
                c = "B2";
            } else if (name.charAt(i) == 'Y') {
                c = "0C";
            } else if (name.charAt(i) == 'z') {
                c = "J2";
            } else if (name.charAt(i) == 'Z') {
                c = "0I";
            } else if (name.charAt(i) == ' ') {
                c = "00";
            } else if (name.charAt(i) == '1') {
                c = "Sz";
            } else if (name.charAt(i) == '2') {
                c = "Ay";
            } else if (name.charAt(i) == '3') {
                c = "Fx";
            } else if (name.charAt(i) == '4') {
                c = "Ew";
            } else if (name.charAt(i) == '5') {
                c = "If";
            } else if (name.charAt(i) == '6') {
                c = "Te";
            } else if (name.charAt(i) == '7') {
                c = "Od";
            } else if (name.charAt(i) == '8') {
                c = "Lc";
            } else if (name.charAt(i) == '9') {
                c = "Ub";
            } else if (name.charAt(i) == '0') {
                c = "Da";
            } else if (name.charAt(i) == ':') {
                c = "01";
            } else if (name.charAt(i) == '!') {
                c = "02";
            } else if (name.charAt(i) == '$') {
                c = "03";
            } else if (name.charAt(i) == '#') {
                c = "04";
            } else if (name.charAt(i) == '*') {
                c = "05";
            } else if (name.charAt(i) == '%') {
                c = "06";
            } else if (name.charAt(i) == '&') {
                c = "07";
            } else if (name.charAt(i) == '/') {
                c = "08";
            } else if (name.charAt(i) == '(') {
                c = "09";
            } else if (name.charAt(i) == ')') {
                c = "10";
            } else if (name.charAt(i) == '=') {
                c = "11";
            } else if (name.charAt(i) == '¿') {
                c = "12";
            } else if (name.charAt(i) == '¡') {
                c = "13";
            } else if (name.charAt(i) == '?') {
                c = "14";
            } else {
                c = "";
            }
            mnsjN = mnsjN + c;

        }
        return mnsjN;
    }

}
