package application;
	
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;



public class Main extends Application {
	
	Socket socket;
	TextArea textArea;
	
	//클라이언트 프로그램 동작 메소드입니다.
	//서버의 IP와port가 그대로 들어감 어떤 IP와port번호로 접속할지 설정해주는것
	public void startClient(String IP,int port) {
		Thread thread = new Thread() {
			
			public void run() {
				try {
					//소켓 초기화
					socket = new Socket(IP, port);
					//초기화 이후 서버로부터 어떠한 메시지를 전달받을수있도록
					receive();
				}catch(Exception e) {
					//오류 발생시 소켓이 열려잇다면
					if(!socket.isClosed()) {
						//종료
						stopClient();
						System.out.println("[서버 접속 실패]");
						//프로그램 자체를 종료
						Platform.exit();
					}
				}
			}
		};
		thread.start();
	}
	//클라이언트 프로그램 종료 메소드입니다.
	public void stopClient() {
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//서버로부터 메시지를 전달받는 메소드입니다.
	public void receive() {
		//서버로부터 메시지를 계속 받기위해 무한루프
		while(true) {
			try {
				//무한루프돌면서 소켓에서 InputStream을 열어서 현재의 서버로부터 어떠한 메시지를 전달받을수있도록 만듬
				InputStream in = socket.getInputStream();
				//버퍼에 512만큼 끊어서 buffer에 담아 전달받음
				byte[] buffer = new byte[512];
				//read함수이용해서 실제입력받음
				int length = in.read(buffer);
				//오류발생시
				if(length == -1) throw new IOException();
				//버퍼에 있는 정보를 length만큼 message라는 변수에 담아서
				String message = new String(buffer, 0, length, "UTF-8");
				//화면에 출력
				Platform.runLater(()->{
					textArea.appendText(message);
				});
			}catch(Exception e) {
				//오류발생시 무한루프 탈출
				stopClient();
				break;
			}
		}
	}
	//서버로 메시지를 전송하는 메소드입니다.
	//서버로 전송 스레드한개,서버로부터 전달받는 스레드한개 각각 다른역할을 가진다
	public void send(String message) {
		Thread thread = new Thread() {
			//run함수를 이용해서 스레드가 어떠한 내용으로 동작하는지 명시
			public void run() {
				try {
					//서버로 메시지전송
					OutputStream out = socket.getOutputStream();
					//보내고자하는 메시지를 UTF-8로 인코딩해서 보냄
					byte[] buffer = message.getBytes("UTF-8");
					//메시지전송후 flush로 전송의 끝을 알림
					out.write(buffer);
					out.flush();
				
				}catch(Exception e) {
					stopClient();
				}
			}
		};
		//스레드만든이후에는 실행시키게
		thread.start();
	}
	//실제로 프로그램을 동작시키는 메소드입니다.
	//클라이언트 프로그램을 실질적으로 동작시키는 메소드 
	@Override
	public void start(Stage primaryStage) {
		//레이아웃은 BorderPane으로 작성
		BorderPane root = new BorderPane();
		//setPadding패딩을 줌
		root.setPadding(new Insets(5));
		
		//BorderPane위에 하나의 레이아웃을 더줌
		HBox hbox = new HBox();
		//hbox에 여백을줌
		hbox.setSpacing(5);
		
		//사용자의 이름이 들어갈수있는 텍스트공간을줌
		TextField userName = new TextField();
		//TextField너비 설정
		userName.setPrefWidth(150);
		//기본적으로 보이게
		userName.setPromptText("닉네임을 입력하세요");
		//HBox내부에서 해당 TextField가 출력이 될수있도록 만듬
		HBox.setHgrow(userName,Priority.ALWAYS);
		
		//TextField한개 더 만들어줌 서버의 ip주소가 들어갈수있게 IPText
		TextField IPText = new TextField("127.0.0.1");
		//9876가 들어갈수있게 만듬 portText
		TextField portText = new TextField("9876");
		portText.setPrefWidth(80);
		
		//실질적으로 hbox 내부에 3개의 TextField가 추가될수있게 만듬
		hbox.getChildren().addAll(userName, IPText, portText);
		//hbox를 BorderPane의 위쪽에 달아줌
		root.setTop(hbox);
		
		//객체 초기화
		textArea = new TextArea();
		//setEditable 서버프로그램과 마찬가지로 내용수정불가하게함
		textArea.setEditable(false);
		//setCenter 레이아웃의 중간에 textArea가 보이도록함
		root.setCenter(textArea);
		
		//아래쪽엔 입력창을 만듬
		TextField input = new TextField();
		//너비 구성
		input.setPrefWidth(Double.MAX_VALUE);
		//접속하기이전에 이용할수없도록
		input.setDisable(true);
		
		//이벤트가 발생을 했을때 서버로 어떠한 메시지를 전달할수있도록 만듬 사용자의 이름과함께
		input.setOnAction(event->{
			send(userName.getText() + ": " + input.getText() + "\n");
			//전송후 메시지전송칸 비움
			input.setText("");
			//다시전송하도록 포커스를줌
			input.requestFocus();
		});
		
		//send버튼 만듬
		Button sendButton = new Button("보내기");
		//접속하기이전에 이용할수없도록
		sendButton.setDisable(true);
		
		//버튼을 누른경우 이벤트처리
		sendButton.setOnAction(event->{
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");
			input.requestFocus();
		});
		
		Button connectionButton = new Button("접속하기");
		connectionButton.setOnAction(event ->{
			if(connectionButton.getText().equals("접속하기")) {
				//포트번호 설정됨
				int port = 9876;
				try {
					//사용자가 입력한 포트번호 입력칸에 들어있는 텍스트내용을 정수형태로 변환해서 다시 담을수있도록함
					//기본9876설정이고 사용자가 별도의 포트번호를 입력하면 그포트로 접속하게됨 
					port = Integer.parseInt(portText.getText());
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				//특정한 ip주소에 어떠한 포트번호로 접속할수있게함 
				startClient(IPText.getText(), port);
				Platform.runLater(()->{
					textArea.appendText("[채팅방 접속 ]\n");
				});
				//접속이 이루어져서 종료하기로 바꿔줌
				connectionButton.setText("종료하기");
				input.setDisable(false);
				//전송할수있도록 false로 처리
				sendButton.setDisable(false);
				input.requestFocus();
			}else {
				//종료하기 버튼이면
				stopClient();
				//메시지 출력
				Platform.runLater(()->{
					textArea.appendText("[채팅방 퇴장 ]\n");
				});
				//버튼 내용을 바꿈
				connectionButton.setText("접속하기");
				//입력칸 및 버튼을 누를 수 없도록 설정
				input.setDisable(true);
				sendButton.setDisable(true);
			}
		});
		
		//위에서 정의한 내용이 들어갈수있게
		BorderPane pane = new BorderPane();
		//pane왼쪽에 connectionButton 
		pane.setLeft(connectionButton);
		//중간에
		pane.setCenter(input);
		//오른쪽
		pane.setRight(sendButton);
		//아래
		root.setBottom(pane);
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setTitle("[ 채팅 클라이언트 ]");
		//방금만든 씬을 등록
		primaryStage.setScene(scene);
		//닫기를 눌렀다면 stopClient수행 후 종료가 이루어짐
		primaryStage.setOnCloseRequest(event -> stopClient());
		//세팅이 끝나면 show
		primaryStage.show();
		
		//프로그램이 실행이되면 접속하기 버튼이 포커싱
		connectionButton.requestFocus();
	}
	
	//프로그램의 진입점입니다.
	public static void main(String[] args) {
		launch(args);
	}
}
