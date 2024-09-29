package View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class ServerThread implements Runnable {
	
	private Socket socketOfServer;
	private int clientNumber;
	private BufferedReader is;
	private BufferedWriter os;
	private boolean isClosed;

	public ServerThread(Socket socketOfServer, int clientNumber) {
		this.socketOfServer = socketOfServer;
		this.clientNumber = clientNumber;
		isClosed = false;
	}

	public Socket getSocketOfServer() {
		return socketOfServer;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public BufferedReader getIs() {
		return is;
	}

	public BufferedWriter getOs() {
		return os;
	}

	public void setOs(BufferedWriter os) {
		this.os = os;
	}


	@Override
	public void run() {
        try {
            // Mở luồng vào ra trên Socket tại Server.
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            write("get-id" + "," + this.clientNumber);
            Server.serverThreadBus.sendOnlineList();
         // Server.serverThreadBus.mutilCastSend("global-message"+","+"---Client "+this.clientNumber+" đã đăng nhập---");
            String message;
            while (!isClosed) {
                message = is.readLine();
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
                if(messageSplit[0].equals("send-to-global")){
                    Server.serverThreadBus.boardCast(this.getClientNumber(),"global-message"+","+messageSplit[2]+": "+messageSplit[1]);
                }if(messageSplit[0].equals("send-to-person")){
                    Server.serverThreadBus.sendMessageToPersion(Integer.parseInt(messageSplit[3]),"Client "+ messageSplit[2]+" (tới bạn): "+messageSplit[1]);
                }if(messageSplit[0].equals("check")) {
                	Server.serverThreadBus.check(this.getClientNumber(),messageSplit[1], messageSplit[2]);
                }if(messageSplit[0].equals("update")) {
                	Server.serverThreadBus.update(this.getClientNumber());
                }if(messageSplit[0].equals("selected")) {
                	Server.serverThreadBus.selected(this.getClientNumber(),messageSplit[1]);
                }if(messageSplit[0].equals("productStaff")) {
                	Server.serverThreadBus.productStaff(this.getClientNumber());
                }if(messageSplit[0].equals("tongtien")) {
                	Server.serverThreadBus.tongtien(this.getClientNumber(),Integer.parseInt(messageSplit[1]),messageSplit[2]);
                }if(messageSplit[0].equals("thongke")) {
                	Server.serverThreadBus.thongke(this.getClientNumber());
                }if(messageSplit[0].equals("staff")) {
                	Server.serverThreadBus.staff(this.getClientNumber());
                }if(messageSplit[0].equals("chienluoc")) {
                	Server.serverThreadBus.chienluoc(this.getClientNumber());
                }if(messageSplit[0].equals("dangkytaikhoannhanvien")) {
                	Server.serverThreadBus.dangkytaikhoannhanvien(this.getClientNumber(),messageSplit[1],messageSplit[2],messageSplit[3]);
                }if(messageSplit[0].equals("kiemtranhanvientontaihaychua")) {
                	Server.serverThreadBus.kiemtranhanvientontaihaychua(this.getClientNumber(),messageSplit[1],messageSplit[2],messageSplit[3],messageSplit[4],messageSplit[5],messageSplit[6]);
                }if(messageSplit[0].equals("delete")) {
                	Server.serverThreadBus.delete(this.getClientNumber(),messageSplit[1]);
                }
            }
        } catch (IOException e) {
            isClosed = true;
            Server.serverThreadBus.remove(clientNumber);
            Server.serverThreadBus.sendOnlineList();
          //  Server.serverThreadBus.mutilCastSend("global-message"+","+"---Client "+this.clientNumber+" đã thoát---");
        }
    }
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
}
