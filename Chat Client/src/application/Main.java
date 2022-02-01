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
	
	//Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ��Դϴ�.
	//������ IP��port�� �״�� �� � IP��port��ȣ�� �������� �������ִ°�
	public void startClient(String IP,int port) {
		Thread thread = new Thread() {
			
			public void run() {
				try {
					//���� �ʱ�ȭ
					socket = new Socket(IP, port);
					//�ʱ�ȭ ���� �����κ��� ��� �޽����� ���޹������ֵ���
					receive();
				}catch(Exception e) {
					//���� �߻��� ������ �����մٸ�
					if(!socket.isClosed()) {
						//����
						stopClient();
						System.out.println("[���� ���� ����]");
						//���α׷� ��ü�� ����
						Platform.exit();
					}
				}
			}
		};
		thread.start();
	}
	//Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ��Դϴ�.
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
	//�����κ��� �޽����� ���޹޴� �޼ҵ��Դϴ�.
	public void receive() {
		//�����κ��� �޽����� ��� �ޱ����� ���ѷ���
		while(true) {
			try {
				//���ѷ������鼭 ���Ͽ��� InputStream�� ��� ������ �����κ��� ��� �޽����� ���޹������ֵ��� ����
				InputStream in = socket.getInputStream();
				//���ۿ� 512��ŭ ��� buffer�� ��� ���޹���
				byte[] buffer = new byte[512];
				//read�Լ��̿��ؼ� �����Է¹���
				int length = in.read(buffer);
				//�����߻���
				if(length == -1) throw new IOException();
				//���ۿ� �ִ� ������ length��ŭ message��� ������ ��Ƽ�
				String message = new String(buffer, 0, length, "UTF-8");
				//ȭ�鿡 ���
				Platform.runLater(()->{
					textArea.appendText(message);
				});
			}catch(Exception e) {
				//�����߻��� ���ѷ��� Ż��
				stopClient();
				break;
			}
		}
	}
	//������ �޽����� �����ϴ� �޼ҵ��Դϴ�.
	//������ ���� �������Ѱ�,�����κ��� ���޹޴� �������Ѱ� ���� �ٸ������� ������
	public void send(String message) {
		Thread thread = new Thread() {
			//run�Լ��� �̿��ؼ� �����尡 ��� �������� �����ϴ��� ���
			public void run() {
				try {
					//������ �޽�������
					OutputStream out = socket.getOutputStream();
					//���������ϴ� �޽����� UTF-8�� ���ڵ��ؼ� ����
					byte[] buffer = message.getBytes("UTF-8");
					//�޽��������� flush�� ������ ���� �˸�
					out.write(buffer);
					out.flush();
				
				}catch(Exception e) {
					stopClient();
				}
			}
		};
		//�����常�����Ŀ��� �����Ű��
		thread.start();
	}
	//������ ���α׷��� ���۽�Ű�� �޼ҵ��Դϴ�.
	//Ŭ���̾�Ʈ ���α׷��� ���������� ���۽�Ű�� �޼ҵ� 
	@Override
	public void start(Stage primaryStage) {
		//���̾ƿ��� BorderPane���� �ۼ�
		BorderPane root = new BorderPane();
		//setPadding�е��� ��
		root.setPadding(new Insets(5));
		
		//BorderPane���� �ϳ��� ���̾ƿ��� ����
		HBox hbox = new HBox();
		//hbox�� ��������
		hbox.setSpacing(5);
		
		//������� �̸��� �����ִ� �ؽ�Ʈ��������
		TextField userName = new TextField();
		//TextField�ʺ� ����
		userName.setPrefWidth(150);
		//�⺻������ ���̰�
		userName.setPromptText("�г����� �Է��ϼ���");
		//HBox���ο��� �ش� TextField�� ����� �ɼ��ֵ��� ����
		HBox.setHgrow(userName,Priority.ALWAYS);
		
		//TextField�Ѱ� �� ������� ������ ip�ּҰ� �����ְ� IPText
		TextField IPText = new TextField("127.0.0.1");
		//9876�� �����ְ� ���� portText
		TextField portText = new TextField("9876");
		portText.setPrefWidth(80);
		
		//���������� hbox ���ο� 3���� TextField�� �߰��ɼ��ְ� ����
		hbox.getChildren().addAll(userName, IPText, portText);
		//hbox�� BorderPane�� ���ʿ� �޾���
		root.setTop(hbox);
		
		//��ü �ʱ�ȭ
		textArea = new TextArea();
		//setEditable �������α׷��� ���������� ��������Ұ��ϰ���
		textArea.setEditable(false);
		//setCenter ���̾ƿ��� �߰��� textArea�� ���̵�����
		root.setCenter(textArea);
		
		//�Ʒ��ʿ� �Է�â�� ����
		TextField input = new TextField();
		//�ʺ� ����
		input.setPrefWidth(Double.MAX_VALUE);
		//�����ϱ������� �̿��Ҽ�������
		input.setDisable(true);
		
		//�̺�Ʈ�� �߻��� ������ ������ ��� �޽����� �����Ҽ��ֵ��� ���� ������� �̸����Բ�
		input.setOnAction(event->{
			send(userName.getText() + ": " + input.getText() + "\n");
			//������ �޽�������ĭ ���
			input.setText("");
			//�ٽ������ϵ��� ��Ŀ������
			input.requestFocus();
		});
		
		//send��ư ����
		Button sendButton = new Button("������");
		//�����ϱ������� �̿��Ҽ�������
		sendButton.setDisable(true);
		
		//��ư�� ������� �̺�Ʈó��
		sendButton.setOnAction(event->{
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");
			input.requestFocus();
		});
		
		Button connectionButton = new Button("�����ϱ�");
		connectionButton.setOnAction(event ->{
			if(connectionButton.getText().equals("�����ϱ�")) {
				//��Ʈ��ȣ ������
				int port = 9876;
				try {
					//����ڰ� �Է��� ��Ʈ��ȣ �Է�ĭ�� ����ִ� �ؽ�Ʈ������ �������·� ��ȯ�ؼ� �ٽ� �������ֵ�����
					//�⺻9876�����̰� ����ڰ� ������ ��Ʈ��ȣ�� �Է��ϸ� ����Ʈ�� �����ϰԵ� 
					port = Integer.parseInt(portText.getText());
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				//Ư���� ip�ּҿ� ��� ��Ʈ��ȣ�� �����Ҽ��ְ��� 
				startClient(IPText.getText(), port);
				Platform.runLater(()->{
					textArea.appendText("[ä�ù� ���� ]\n");
				});
				//������ �̷������ �����ϱ�� �ٲ���
				connectionButton.setText("�����ϱ�");
				input.setDisable(false);
				//�����Ҽ��ֵ��� false�� ó��
				sendButton.setDisable(false);
				input.requestFocus();
			}else {
				//�����ϱ� ��ư�̸�
				stopClient();
				//�޽��� ���
				Platform.runLater(()->{
					textArea.appendText("[ä�ù� ���� ]\n");
				});
				//��ư ������ �ٲ�
				connectionButton.setText("�����ϱ�");
				//�Է�ĭ �� ��ư�� ���� �� ������ ����
				input.setDisable(true);
				sendButton.setDisable(true);
			}
		});
		
		//������ ������ ������ �����ְ�
		BorderPane pane = new BorderPane();
		//pane���ʿ� connectionButton 
		pane.setLeft(connectionButton);
		//�߰���
		pane.setCenter(input);
		//������
		pane.setRight(sendButton);
		//�Ʒ�
		root.setBottom(pane);
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setTitle("[ ä�� Ŭ���̾�Ʈ ]");
		//��ݸ��� ���� ���
		primaryStage.setScene(scene);
		//�ݱ⸦ �����ٸ� stopClient���� �� ���ᰡ �̷����
		primaryStage.setOnCloseRequest(event -> stopClient());
		//������ ������ show
		primaryStage.show();
		
		//���α׷��� �����̵Ǹ� �����ϱ� ��ư�� ��Ŀ��
		connectionButton.requestFocus();
	}
	
	//���α׷��� �������Դϴ�.
	public static void main(String[] args) {
		launch(args);
	}
}
