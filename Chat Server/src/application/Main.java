package application;
	
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class Main extends Application {
	
	//다양한 클라이언트가 접속했을때 스레드들을 효과적으로 관리        
	public static ExecutorService threadPool;
	//접속한 클라이언트들을 관리     백터는 배열
	public static Vector<Client> clients = new Vector<Client>();
	
	ServerSocket serverSocket;
	
	//서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드입니다.
	//어떠한 아이피와 포트로 열어서 클라이언트와 통신을 할건지 적음
	public void startServer(String IP, int port)
	{
		try {
			//서버가 실행이되면 서버소캣부터 작업 바인드해서 서버 컴퓨터 역할을 수행하는 컴퓨터가 자신의 아이피주소 , 포트번호로
			//특정한 클라이언트의 접속을 기다리도록 할수가있다
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
		}catch(Exception e)
		{
			//닫혀있는 상태가 아니라면 스탑
			if(!serverSocket.isClosed())
			{
				stopServer();
			}
			return;
		}
		//오류가 발생하지않고 성공적으로 서버가 소켓을 잘열어서 접속을 기다릴수있는 상태가 되었다면
		//클라이언트가 접속할 때까지 계속 기다리는 스레드입니다.
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						//접속해서 연결이되면
						Socket socket = serverSocket.accept();
						//클라이언트가 접속을 했다면 클라이언트 배열에 새롭게 접속한 클라이언트를 추가해줌
						clients.add(new Client(socket));
						System.out.println("[클라이언트 접속] "
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
					}catch(Exception e)
					{
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						//빠져나옴
						break;
					}
				}
			}
		};
		//스레드풀을 초기화
		threadPool = Executors.newCachedThreadPool();
		//현재 클라이언트를 기다리는 스레드를 담을수있도록 처리해서 위에서 성공적으로 먼저 초기화 해주고 그안에 첫번째 현재 스레드를 넣어서 관리
		threadPool.submit(thread);
	}
	//서버의 작동을 중지시키는 메소드
	public void stopServer()
	{
		try {
			//현재 작동중인 모든 소켓 닫기
			//Iterator모든 클라이언트에 개별적으로 접근할수있도록 만들어줌
			Iterator<Client> iterator = clients.iterator();
			//클라이언트를 하나씩 접근함
			while(iterator.hasNext()) {
				//특정한 클라이언트에 접근해서
				Client client = iterator.next();
				//그 클라이언트의 소켓을 닫는다
				client.socket.close();
				//iterator에서 해당 끊긴 클라이언트를 제거
				iterator.remove();
			}
			//끊겼으니까 서버 소켓객체 닫기
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			//스레드 풀 종료하기
			if(threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//UI를 생성하고, 실질적으로 프로그램을 동작시키는 메소드입니다.
	@Override
	//GUI작업 처리
	public void start(Stage primaryStage) {
		//전체 디자인 틀을 담을 수 있는 펜
		BorderPane root = new BorderPane();
		//내부에 5만큼 패딩줌
		root.setPadding(new Insets(5));
		
		//긴문장에 텍스트가 담길수있도록 하는 공간
		TextArea textArea = new TextArea();
		//문장을 단순하게 출력만하고 수정은 불가능하게
		textArea.setEditable(false);
		//글씨체는 설치가 되어있어야
		textArea.setFont(new Font("보통", 15));
		root.setCenter(textArea);
		
		//서버의 작동을 시작하도록 만들어주는 버튼     toggleButton=스위치
		Button toggleButton = new Button("시작하기");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		//BorderPane에 마진을 줌
		BorderPane.setMargin(toggleButton,new Insets(1, 0, 0, 0));
		root.setBottom(toggleButton);
		
		String IP = "127.0.0.1";
		int port = 9876;
		
		//toggleButton눌렀을때 액션처리 event
		toggleButton.setOnAction(event ->{
			//시작하기를 포함하고있는 상태라면
			if(toggleButton.getText().equals("시작하기")) {
				//서버 시작
				startServer(IP, port);
				//버튼을 눌렀을때 바로 TextArea에 어떠한 정보를 쓰도록 하면 안되고 항상 runLater함수를 이용해서
				//GUI요소를 출력할수있도록 해야함
				Platform.runLater(() ->{
					String message = String.format("[서버시작]\n",IP,port);
					//비로소 textArea에 메시지를 출력하게됨
					textArea.appendText(message);
					//내용이 종료하기로 바뀌도록 만들어줌
					toggleButton.setText("종료하기");
				});
			}else
			{
				//종료버튼을 눌렀을때 서버를종료
				stopServer();
				//GUL 즉 화면에 어떠한 내용을 출력하도록 사용
				Platform.runLater(() ->{
					String message = String.format("[서버종료]\n",IP,port);
					textArea.appendText(message);
					toggleButton.setText("시작하기");
				});
			}
		});
		
		//화면크기
		Scene scene = new Scene(root, 400, 400);
		//제목설정
		primaryStage.setTitle("[채팅 서버]");
		//종료했다면 stopServer함수를 수행한뒤에 종료
		primaryStage.setOnCloseRequest(event -> stopServer());
		//scene정보를 화면에 정상적으로 출력할수있도록
		primaryStage.setScene(scene);
		//show를 이용해 화면에 출력
		primaryStage.show();
	}
	
	//프로그램의 진입점입니다.
	
	public static void main(String[] args) {
		launch(args);
	}
}
