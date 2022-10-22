import exceptions.UserAlreadyExistsException;
import unibedrooms.UniBedroomsDataBase;
import unibedrooms.UniBedroomsDataBaseClass;

import java.io.*;
import java.util.Scanner;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class Main {

    private static final String DATA_FILE = "uniBedroomsData.dat";

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
        UniBedroomsDataBase data = load();
        Scanner in = new Scanner(System.in);
        Command com = null;

        while(com!=Command.XS){
            System.out.print(">");
            com = getCommand(in);
            switch (com){
                case IE:
                    addStudent(in,data);
                    break;
                case DE:
                    studentData(in,data);
                    break;
                case IG:
                    addManager(in,data);
                    break;
                case DG:
                    managerData(in,data);
                    break;
                case IQ:
                    addRoom(in,data);
                    break;
                case DQ:
                    roomData(in,data);
                    break;
                case MQ:
                    modifyRoomState(in,data);
                    break;
                case RQ:
                    removeRoom(in,data);
                    break;
                case IC:
                    addCandidature(in,data);
                    break;
                case AC :
                    acceptCandidature(in,data);
                    break;
                case LC:
                    listRoomCandidatures(in,data);
                    break;
                case LQ:
                    break;
                case LL:
                    break;
                case XS:
                    System.out.println(Msg.EXIT_MSG);
                    break;
                default:
                    break;
            }
            System.out.println();
        }
        in.close();
        save(data);
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


    /**
     * Adds a student to the system
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void addStudent(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        String nome=in.nextLine().trim();
        int idade=in.nextInt();
        String localidade=in.nextLine().trim();
        String universidade=in.nextLine();
        System.out.println();

        try{
            data.addStudent(login,nome,idade,localidade,universidade);
            System.out.println(Msg.STUDENT_ADDED);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }


    }
    private static void studentData(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        in.nextLine();
        System.out.println();

        //System.out.printf(Msg.STUDENT_DATA);
    }
    private static void addManager(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        String nome=in.nextLine().trim();
        in.nextLine();
        String universidade=in.nextLine();
        System.out.println();

        System.out.println(Msg.MANAGER_ADDED);
    }
    private static void managerData(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        in.nextLine();
        System.out.println();

        //System.out.printf(Msg.MANAGER_DATA);
    }
    private static void addRoom(Scanner in, UniBedroomsDataBase data){
        String codigo = in.next();
        String login = in.next();
        in.nextLine();
        String nomeResidencia= in.nextLine();
        String universidade = in.nextLine();
        String localidade = in.nextLine();
        int andar = in.nextInt();
        in.nextLine();
        String descricao = in.nextLine();
        System.out.println();

        System.out.println(Msg.ROOM_ADDED);

    }
    private static void roomData(Scanner in, UniBedroomsDataBase data){
    }
    private static void modifyRoomState(Scanner in, UniBedroomsDataBase data){
    }
    private static void removeRoom(Scanner in, UniBedroomsDataBase data){
    }
    private static void addCandidature(Scanner in, UniBedroomsDataBase data){
    }
    private static void acceptCandidature(Scanner in, UniBedroomsDataBase data){
    }
    private static void listRoomCandidatures(Scanner in, UniBedroomsDataBase data){
    }

    /**
     * Loads the program from an external file if the file exists
     *
     * @return
     */
    private static UniBedroomsDataBase load(){
        UniBedroomsDataBase data;
        try{
            ObjectInputStream file = new ObjectInputStream(
                    new FileInputStream(DATA_FILE)
            );
            data = (UniBedroomsDataBase) file.readObject();
            file.close();
        } catch (IOException | ClassNotFoundException e){
            data = new UniBedroomsDataBaseClass();
        }
        return data;
    }


    /**
     * Saves the program to an external file, creates a file if there is not one already
     *
     * @param data
     */
    private static void save(UniBedroomsDataBase data){
        try{
            ObjectOutputStream file = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE)
            );
            file.writeObject(data);
            file.flush();
            file.close();
        } catch(IOException e){
            System.out.println("Error saving: "+e.getMessage());
        }
    }

}
