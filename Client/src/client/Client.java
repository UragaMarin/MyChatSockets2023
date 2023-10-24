package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;

    public Client(Socket socket, String name) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = name;
        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    public void sendMessage() {
        try {
            buffWriter.write(name);
            buffWriter.newLine();
            buffWriter.flush();
            Scanner sc = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = (name + ": " + sc.nextLine());
                String cifrado = Cifrar(messageToSend);
                buffWriter.write(cifrado);
                buffWriter.newLine();
                buffWriter.flush();
            }
        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    public void readMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msfFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msfFromGroupChat = buffReader.readLine();
                        String descifrado = Descifrar(msfFromGroupChat);
                        System.out.println(descifrado);
                    } catch (IOException e) {
                        closeAll(socket, buffReader, buffWriter);
                    }
                }
            }

        }).start();
    }

    private String Descifrar(String msfFromGroupChat) {
        String mnsjN = "";
        int length = msfFromGroupChat.length();

        for (int i = 0; i < length; i += 2) {
            if (i + 1 < length) {
                String c = Character.toString(msfFromGroupChat.charAt(i)) + Character.toString(msfFromGroupChat.charAt(i + 1));

                char letra = 'a';

                if (c.equals("O2")) {
                    letra = 'a';
                } else if (c.equals("2T")) {
                    letra = 'A';
                } else if (c.equals("G1")) {
                    letra = 'b';
                } else if (c.equals("2G")) {
                    letra = 'B';
                } else if (c.equals("J1")) {
                    letra = 'c';
                } else if (c.equals("2S")) {
                    letra = 'C';
                } else if (c.equals("I1")) {
                    letra = 'd';
                } else if (c.equals("2Y")) {
                    letra = 'D';
                } else if (c.equals("C1")) {
                    letra = 'e';
                } else if (c.equals("2B")) {
                    letra = 'E';
                } else if (c.equals("A1")) {
                    letra = 'f';
                } else if (c.equals("2R")) {
                    letra = 'F';
                } else if (c.equals("S1")) {
                    letra = 'g';
                } else if (c.equals("2I")) {
                    letra = 'G';
                } else if (c.equals("Z1")) {
                    letra = 'h';
                } else if (c.equals("2Q")) {
                    letra = 'H';
                } else if (c.equals("N1")) {
                    letra = 'i';
                } else if (c.equals("1q")) {
                    letra = 'I';
                } else if (c.equals("F1")) {
                    letra = 'j';
                } else if (c.equals("1X")) {
                    letra = 'J';
                } else if (c.equals("T1")) {
                    letra = 'k';
                } else if (c.equals("1Z")) {
                    letra = 'K';
                } else if (c.equals("E0")) {
                    letra = 'l';
                } else if (c.equals("1G")) {
                    letra = 'L';
                } else if (c.equals("W0")) {
                    letra = 'm';
                } else if (c.equals("1O")) {
                    letra = 'M';
                } else if (c.equals("M0")) {
                    letra = 'n';
                } else if (c.equals("1Y")) {
                    letra = 'N';
                } else if (c.equals("V0")) {
                    letra = 'ñ';
                } else if (c.equals("1S")) {
                    letra = 'Ñ';
                } else if (c.equals("X0")) {
                    letra = 'o';
                } else if (c.equals("1J")) {
                    letra = 'O';
                } else if (c.equals("H0")) {
                    letra = 'p';
                } else if (c.equals("1I")) {
                    letra = 'P';
                } else if (c.equals("D0")) {
                    letra = 'q';
                } else if (c.equals("1Q")) {
                    letra = 'Q';
                } else if (c.equals("P0")) {
                    letra = 'r';
                } else if (c.equals("0q")) {
                    letra = 'R';
                } else if (c.equals("L0")) {
                    letra = 's';
                } else if (c.equals("0W")) {
                    letra = 'S';
                } else if (c.equals("K2")) {
                    letra = 't';
                } else if (c.equals("0P")) {
                    letra = 'T';
                } else if (c.equals("R2")) {
                    letra = 'u';
                } else if (c.equals("0G")) {
                    letra = 'U';
                } else if (c.equals("Y2")) {
                    letra = 'v';
                } else if (c.equals("0S")) {
                    letra = 'V';
                } else if (c.equals("U2")) {
                    letra = 'w';
                } else if (c.equals("0E")) {
                    letra = 'W';
                } else if (c.equals("Q2")) {
                    letra = 'x';
                } else if (c.equals("0B")) {
                    letra = 'X';
                } else if (c.equals("B2")) {
                    letra = 'y';
                } else if (c.equals("0C")) {
                    letra = 'Y';
                } else if (c.equals("J2")) {
                    letra = 'z';
                } else if (c.equals("0I")) {
                    letra = 'Z';
                } else if (c.equals("00")) {
                    letra = ' ';
                } else if (c.equals("Sz")) {
                    letra = '1';
                } else if (c.equals("Ay")) {
                    letra = '2';
                } else if (c.equals("Fx")) {
                    letra = '3';
                } else if (c.equals("Ew")) {
                    letra = '4';
                } else if (c.equals("If")) {
                    letra = '5';
                } else if (c.equals("Te")) {
                    letra = '6';
                } else if (c.equals("Od")) {
                    letra = '7';
                } else if (c.equals("Lc")) {
                    letra = '8';
                } else if (c.equals("Ub")) {
                    letra = '9';
                } else if (c.equals("Da")) {
                    letra = '0';
                } else if (c.equals("01")) {
                    letra = ':';
                } else if (c.equals("02")) {
                    letra = '!';
                } else if (c.equals("03")) {
                    letra = '$';
                } else if (c.equals("04")) {
                    letra = '#';
                } else if (c.equals("05")) {
                    letra = '*';
                } else if (c.equals("06")) {
                    letra = '%';
                } else if (c.equals("07")) {
                    letra = '&';
                } else if (c.equals("08")) {
                    letra = '/';
                } else if (c.equals("09")) {
                    letra = '(';
                } else if (c.equals("10")) {
                    letra = ')';
                } else if (c.equals("11")) {
                    letra = '=';
                } else if (c.equals("12")) {
                    letra = '¿';
                } else if (c.equals("13")) {
                    letra = '¡';
                } else if (c.equals("14")) {
                    letra = '?';
                } else {
                    letra = '\0';
                }
                mnsjN = mnsjN + Character.toString(letra);
            } else {
                mnsjN = mnsjN + Character.toString('\0');
            }
        }
        return mnsjN;
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
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

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu nombre");
        String name = sc.nextLine();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, name);
        client.readMessage();
        client.sendMessage();
    }

    private String Cifrar(String messageToSend) {
        String mnsjN = "";

        for (int i = 0; i < messageToSend.length(); i++) {

            String c = "";

            if (messageToSend.charAt(i) == 'a') {
                c = "O2";
            } else if (messageToSend.charAt(i) == 'A') {
                c = "2T";
            } else if (messageToSend.charAt(i) == 'b') {
                c = "G1";
            } else if (messageToSend.charAt(i) == 'B') {
                c = "2G";
            } else if (messageToSend.charAt(i) == 'c') {
                c = "J1";
            } else if (messageToSend.charAt(i) == 'C') {
                c = "2S";
            } else if (messageToSend.charAt(i) == 'd') {
                c = "I1";
            } else if (messageToSend.charAt(i) == 'D') {
                c = "2Y";
            } else if (messageToSend.charAt(i) == 'e') {
                c = "C1";
            } else if (messageToSend.charAt(i) == 'E') {
                c = "2B";
            } else if (messageToSend.charAt(i) == 'f') {
                c = "A1";
            } else if (messageToSend.charAt(i) == 'F') {
                c = "2R";
            } else if (messageToSend.charAt(i) == 'g') {
                c = "S1";
            } else if (messageToSend.charAt(i) == 'G') {
                c = "2I";
            } else if (messageToSend.charAt(i) == 'h') {
                c = "Z1";
            } else if (messageToSend.charAt(i) == 'H') {
                c = "2Q";
            } else if (messageToSend.charAt(i) == 'i') {
                c = "N1";
            } else if (messageToSend.charAt(i) == 'I') {
                c = "1q";
            } else if (messageToSend.charAt(i) == 'j') {
                c = "F1";
            } else if (messageToSend.charAt(i) == 'J') {
                c = "1X";
            } else if (messageToSend.charAt(i) == 'k') {
                c = "T1";
            } else if (messageToSend.charAt(i) == 'K') {
                c = "1Z";
            } else if (messageToSend.charAt(i) == 'l') {
                c = "E0";
            } else if (messageToSend.charAt(i) == 'L') {
                c = "1G";
            } else if (messageToSend.charAt(i) == 'm') {
                c = "W0";
            } else if (messageToSend.charAt(i) == 'M') {
                c = "1O";
            } else if (messageToSend.charAt(i) == 'n') {
                c = "M0";
            } else if (messageToSend.charAt(i) == 'N') {
                c = "1Y";
            } else if (messageToSend.charAt(i) == 'ñ') {
                c = "V0";
            } else if (messageToSend.charAt(i) == 'Ñ') {
                c = "1S";
            } else if (messageToSend.charAt(i) == 'o') {
                c = "X0";
            } else if (messageToSend.charAt(i) == 'O') {
                c = "1J";
            } else if (messageToSend.charAt(i) == 'p') {
                c = "H0";
            } else if (messageToSend.charAt(i) == 'P') {
                c = "1I";
            } else if (messageToSend.charAt(i) == 'q') {
                c = "D0";
            } else if (messageToSend.charAt(i) == 'Q') {
                c = "1Q";
            } else if (messageToSend.charAt(i) == 'r') {
                c = "P0";
            } else if (messageToSend.charAt(i) == 'R') {
                c = "0q";
            } else if (messageToSend.charAt(i) == 's') {
                c = "L0";
            } else if (messageToSend.charAt(i) == 'S') {
                c = "0W";
            } else if (messageToSend.charAt(i) == 't') {
                c = "K2";
            } else if (messageToSend.charAt(i) == 'T') {
                c = "0P";
            } else if (messageToSend.charAt(i) == 'u') {
                c = "R2";
            } else if (messageToSend.charAt(i) == 'U') {
                c = "0G";
            } else if (messageToSend.charAt(i) == 'v') {
                c = "Y2";
            } else if (messageToSend.charAt(i) == 'V') {
                c = "0S";
            } else if (messageToSend.charAt(i) == 'w') {
                c = "U2";
            } else if (messageToSend.charAt(i) == 'W') {
                c = "0E";
            } else if (messageToSend.charAt(i) == 'x') {
                c = "Q2";
            } else if (messageToSend.charAt(i) == 'X') {
                c = "0B";
            } else if (messageToSend.charAt(i) == 'y') {
                c = "B2";
            } else if (messageToSend.charAt(i) == 'Y') {
                c = "0C";
            } else if (messageToSend.charAt(i) == 'z') {
                c = "J2";
            } else if (messageToSend.charAt(i) == 'Z') {
                c = "0I";
            } else if (messageToSend.charAt(i) == ' ') {
                c = "00";
            } else if (messageToSend.charAt(i) == '1') {
                c = "Sz";
            } else if (messageToSend.charAt(i) == '2') {
                c = "Ay";
            } else if (messageToSend.charAt(i) == '3') {
                c = "Fx";
            } else if (messageToSend.charAt(i) == '4') {
                c = "Ew";
            } else if (messageToSend.charAt(i) == '5') {
                c = "If";
            } else if (messageToSend.charAt(i) == '6') {
                c = "Te";
            } else if (messageToSend.charAt(i) == '7') {
                c = "Od";
            } else if (messageToSend.charAt(i) == '8') {
                c = "Lc";
            } else if (messageToSend.charAt(i) == '9') {
                c = "Ub";
            } else if (messageToSend.charAt(i) == '0') {
                c = "Da";
            } else if (messageToSend.charAt(i) == ':') {
                c = "01";
            } else if (messageToSend.charAt(i) == '!') {
                c = "02";
            } else if (messageToSend.charAt(i) == '$') {
                c = "03";
            } else if (messageToSend.charAt(i) == '#') {
                c = "04";
            } else if (messageToSend.charAt(i) == '*') {
                c = "05";
            } else if (messageToSend.charAt(i) == '%') {
                c = "06";
            } else if (messageToSend.charAt(i) == '&') {
                c = "07";
            } else if (messageToSend.charAt(i) == '/') {
                c = "08";
            } else if (messageToSend.charAt(i) == '(') {
                c = "09";
            } else if (messageToSend.charAt(i) == ')') {
                c = "10";
            } else if (messageToSend.charAt(i) == '=') {
                c = "11";
            } else if (messageToSend.charAt(i) == '¿') {
                c = "12";
            } else if (messageToSend.charAt(i) == '¡') {
                c = "13";
            } else if (messageToSend.charAt(i) == '?') {
                c = "14";
            } else {
                c = "";
            }
            mnsjN = mnsjN + c;

        }
        return mnsjN;
    }
}
