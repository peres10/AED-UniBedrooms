import java.util.Scanner;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 *
 */
public class Main {

    private static final String DATA_FILE = "";

    /**
     * Commands used in the console
     */
    private enum Command {
        IE,DE,IG,DG,IQ,DQ,MQ,RQ,IC,AC,LC,LQ,LL,XS,UNKNOWN
    }

    /**
     * System messages and formats
     */
    private enum Msg{
        STUDENT_ADDED("Registo de estudante executado."),STUDENT_DATA("%s %s %d\n%s\n%s\n"),
        MANAGER_ADDED("Registo de gerente executado."),MANAGER_DATA("%s %s\n%s\n"),
        ROOM_ADDED("Registo de quarto executado."),ROOM_DATA("%s %s\n%s\n%d\n%s\n%s\n"),
        ROOM_MODIFIED("Estado de quarto actualizado."),ROOM_REMOVED("Remocao de quarto executada."),
        CANDIDATURE_ADDED("Registo de candidatura executado."),CANDIDATURE_ACCEPTED("Aceitacao de candidatura executada."),
        CANDIDATURE_TO_ROOM_LIST_FORMAT("%s %s %s\n"),ROOM_LIST_FORMAT("\n%s %s\n%s\n%s\n\n"),
        EXIT_MSG("Obrigado. Ate a proxima.");

        private final String msg ;

        Msg(String msg){
            this.msg=msg;
        }
        String getMsg(){
            return msg;
        }
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        commands();
    }

    /**
     * Command interpreter
     */
    private static void commands(){
        Scanner in = new Scanner(System.in);
        Command com = null;
        while(com!=Command.XS){
            com = getCommand(in);
            switch (com){
                case IE:
                    addStudent(in);
                    break;
                case DE:
                    studentData(in);
                    break;
                case IG:
                    addManager(in);
                    break;
                case DG:
                    managerData(in);
                    break;
                case IQ:
                    addRoom(in);
                    break;
                case DQ:
                    roomData(in);
                    break;
                case MQ:
                    modifyRoomState(in);
                    break;
                case RQ:
                    removeRoom(in);
                    break;
                case IC:
                    addCandidature(in);
                    break;
                case AC :
                    acceptCandidature(in);
                    break;
                case LC:
                    listRoomCandidatures(in);
                    break;
                case LQ:
                    break;
                case LL:
                    break;
                case XS:
                    break;
                default:
                    break;
            }
            System.out.println();
        }
        in.close();
    }

    /**
     * Receives a command and verifies if it is a command of the program
     *
     * @param in - Input Scanner
     * @return - a command
     */
    private static Command getCommand(Scanner in){
        try {
            return Command.valueOf(in.next().toUpperCase());
        } catch(IllegalArgumentException e){
            return Command.UNKNOWN;
        }
    }



    private static void addStudent(Scanner in){
    }
    private static void studentData(Scanner in){
    }
    private static void addManager(Scanner in){
    }
    private static void managerData(Scanner in){
    }
    private static void addRoom(Scanner in){
    }
    private static void roomData(Scanner in){
    }
    private static void modifyRoomState(Scanner in){
    }
    private static void removeRoom(Scanner in){
    }
    private static void addCandidature(Scanner in){
    }
    private static void acceptCandidature(Scanner in){
    }
    private static void listRoomCandidatures(Scanner in){
    }



}
