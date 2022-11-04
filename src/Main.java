import exceptions.*;
import unibedrooms.*;

import java.io.*;
import java.util.Scanner;

import dataStructures.Iterator;

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
        ROOM_ADDED("Registo de quarto executado."),ROOM_DATA("%s %s\n%s\n%s\n%d\n%s\n%s\n"),
        ROOM_MODIFIED("Estado de quarto actualizado."),ROOM_REMOVED("Remocao de quarto executada."),
        APPLICATION_ADDED("Registo de candidatura executado."),APPLICATION_ACCEPTED("Aceitacao de candidatura executada."),
        APPLICATION_TO_ROOM_LIST_FORMAT("%s %s %s\n"),ROOM_LIST_FORMAT("\n%s %s\n%s\n%s\n\n"),
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
                    addApplication(in,data);
                    break;
                case AC :
                    acceptApplication(in,data);
                    break;
                case LC:
                    listRoomApplication(in,data);
                    break;
                case LQ:
                    break;
                case LL:
                    break;
                case XS:
                    System.out.println(Msg.EXIT_MSG.getMsg());
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
        String universidade=in.nextLine().trim();

        try{
            data.addStudent(login,nome,idade,localidade,universidade);
            System.out.println(Msg.STUDENT_ADDED.getMsg());
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the information of a student
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void studentData(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        in.nextLine();

        try{
            Student student = data.getStudent(login);
            System.out.printf(Msg.STUDENT_DATA.getMsg(),student.getLogin(),student.getName(),student.getAge(),student.getLocal(),student.getUniversityName());
        } catch (StudentDoesNotExistException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Adds a manager to the system
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void addManager(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        String nome=in.nextLine().trim();
        String universidade=in.nextLine();

        try{
            data.addManager(login,nome,universidade);
            System.out.println(Msg.MANAGER_ADDED.getMsg());
        } catch (UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Gets the information of a manager
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void managerData(Scanner in, UniBedroomsDataBase data){
        String login=in.next();
        in.nextLine();

        try{
            Manager manager = data.getManager(login);
            System.out.printf(Msg.MANAGER_DATA.getMsg(),manager.getLogin(),manager.getName(),manager.getUniversityName());
        } catch(ManagerDoesNotExistException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a room to the system
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void addRoom(Scanner in, UniBedroomsDataBase data){
        String codigo = in.next();
        String login = in.next();
        in.nextLine();
        String nomeResidencia= in.nextLine().trim();
        String universidade = in.nextLine().trim();
        String localidade = in.nextLine().trim();
        int andar = in.nextInt();
        in.nextLine();
        String descricao = in.nextLine().trim();

        try{
            data.addRoom(codigo,login,nomeResidencia,universidade,localidade,andar,descricao);
            System.out.println(Msg.ROOM_ADDED.getMsg());
        } catch(RoomAlreadyExistsException e){
            System.out.println(e.getMessage());
        } catch (ManagerDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch (NonAuthorizedOperationException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Gets the information of a room
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void roomData(Scanner in, UniBedroomsDataBase data){
        String codigo = in.next();
        in.nextLine();
        try{
            Room room = data.getRoom(codigo);
            System.out.printf(Msg.ROOM_DATA.getMsg(),room.getRoomCode(),room.getResidence(),room.getUniversityName(),room.getLocal(),room.getFloor(),room.getDescription(),room.getEstado());
        } catch(RoomDoesNotExistException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Modifies a state of a room
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void modifyRoomState(Scanner in, UniBedroomsDataBase data){
        String codigo = in.next();
        String loginGerente = in.next();
        in.nextLine();
        String estado = in.nextLine();
        try{
            data.updateRoomState(codigo,loginGerente,estado);
            System.out.println(Msg.ROOM_MODIFIED.getMsg());
        } catch (RoomDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch (NonAuthorizedOperationException e){
            System.out.println(e.getMessage());
        } catch (ActiveApplicationException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes a room from the system
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void removeRoom(Scanner in, UniBedroomsDataBase data){
        String codigo = in.next();
        String loginManager = in.next();
        in.nextLine();

        try{
            data.removeRoom(codigo,loginManager);
            System.out.println(Msg.ROOM_REMOVED.getMsg());
        } catch(RoomDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch(NonAuthorizedOperationException e){
            System.out.println(e.getMessage());
        } catch(ActiveApplicationException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an application to a room
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void addApplication(Scanner in, UniBedroomsDataBase data){
        String login = in.next();
        String codigo = in.next();
        in.nextLine();

        try{
            data.insertApplication(login,codigo);
            System.out.println(Msg.APPLICATION_ADDED.getMsg());
        } catch(StudentDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch(NonAuthorizedOperationException e){
            System.out.println(e.getMessage());
        } catch (RoomDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch (RoomOccupiedException e){
            System.out.println(e.getMessage());
        } catch (AlreadyExistsApplicationException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Accepts an application to a room
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void acceptApplication(Scanner in, UniBedroomsDataBase data){
        String codigo = in.next();
        String loginGerente = in.next();
        String loginEstudante = in.next();
        in.nextLine();

        try{
            data.acceptApplication(codigo, loginGerente, loginEstudante);
            System.out.println(Msg.APPLICATION_ACCEPTED.getMsg());
        } catch (RoomDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch(NonAuthorizedOperationException e){
            System.out.println(e.getMessage());
        } catch (ApplicationDoesNotExistException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lists the applications to a room
     *
     * @param in - Input Scanner
     * @param data - UniBedrooms data
     */
    private static void listRoomApplication(Scanner in, UniBedroomsDataBase data){
    	String codigo = in.next();
        String loginGerente = in.next();
        in.nextLine();
        
        try{
            Iterator<RoomApplication> roomAppIt = data.listApplications(codigo, loginGerente);
            while(roomAppIt.hasNext()) {
            	RoomApplication roomApp = roomAppIt.next();
            	System.out.printf(Msg.APPLICATION_TO_ROOM_LIST_FORMAT.getMsg(), roomApp.getStudent().getLogin(), roomApp.getStudent().getName(), roomApp.getStudent().getUniversityName());
            }
        } catch (RoomDoesNotExistException e){
            System.out.println(e.getMessage());
        } catch(NonAuthorizedOperationException e){
            System.out.println(e.getMessage());
        } catch (NoApplicationsToRoomException e){
            System.out.println(e.getMessage());
        }
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
