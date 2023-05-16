import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JuegoDelGato {
    static char[][] gato = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void imprimirGato() {
        for (int i = 0; i < gato.length; i++) {
            for (int j = 0; j < gato[i].length; j++) {
                System.out.print(gato[i][j]);
                if (j < gato[i].length - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < gato.length - 1) {
                System.out.println("---------");
            }
        }
        System.out.println(" ");
    }

    public static void jugar() throws IOException {
        char jugadorActual = 'X';
        int turnos = 0;

        while (true) {
            imprimirGato();

            if (jugadorActual == 'X') {
                registrarJugada(jugadorActual);
            } else {
                int mejorMovimiento = MiniMaxTerminator.obtenerMejorMovimiento(gato, jugadorActual);
                realizarMovimiento(mejorMovimiento, jugadorActual);
            }

            char ganador = verificarGanador(gato);
            if (ganador != ' ') {
                imprimirGato();
                if (ganador == 'X') {
                    System.out.println("¡Has ganado!");
                } else if (ganador == 'O') {
                    System.out.println("La IA ha ganado.");
                } else {
                    System.out.println("¡Empate!");
                }
                break;
            }

            turnos++;
            if (turnos == 9) {
                imprimirGato();
                System.out.println("¡Empate!");
                break;
            }

            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
        }
    }

    public static void registrarJugada(char caracter) throws IOException {
        boolean salir = false;
        String entrada;
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        int posicion;
        do {
            System.out.println("Selecciona una posición en el tablero (1-9): ");
            entrada = bufer.readLine();
            posicion = Integer.parseInt(entrada) - 1;
            if (posicion >= 0 && posicion < 9 && casillaNoOcupada(posicion)) {
                int fila = posicion / 3;
                int columna = posicion % 3;
                realizarMovimiento(posicion, caracter);
                salir = true;
            } else {
                System.out.println("Casilla no válida, selecciona una posición válida del 1 al 9.");
            }
        } while (!salir);
    }

    public static void realizarMovimiento(int posicion, char caracter) {
        int fila = posicion / 3;
        int columna = posicion % 3;
        gato[fila][columna] = caracter;
    }

    public static boolean casillaNoOcupada(int posicion) {
        int fila = posicion / 3;
        int columna = posicion % 3;
        return gato[fila][columna] == ' ';
    }

    public static char verificarGanador(char[][] tablero) {
        // Verificar filas
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != ' ') {
                return tablero[i][0];
            }
        }
        // Verificar columnas
        for (int j = 0; j < tablero.length; j++) {
            if (tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j] && tablero[0][j] != ' ') {
                return tablero[0][j];
            }
        }
        // Verificar diagonales
        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != ' ') {
            return tablero[0][0];
        }
        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != ' ') {
            return tablero[0][2];
        }

        // Si no hay ganador, retornar espacio en blanco
        return ' ';
    }
    
    public static char getOpponent(char jugador) {
        return (jugador == 'X') ? 'O' : 'X';
    }

    public static boolean esTableroLleno(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void otraPartida() throws IOException {
        boolean jugarOtraPartida=true;
        String entrada;
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));

        while(jugarOtraPartida){
            System.out.println("¿Quieres jugar otra partida?[si/no]: ");
            entrada = bufer.readLine().toLowerCase();
            if(entrada.equals("si") || entrada.equals("s")){
                reiniciarTablero();
                jugar();
            }
            else if (entrada.equals("no") || entrada.equals("n")) {
                    jugarOtraPartida = false;
                } else {
                    System.out.println("Entrada inválida. Por favor, responde con 'si' o 'no'.");
                }
        }
    }

    public static void reiniciarTablero() throws IOException {
        gato = new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

}




