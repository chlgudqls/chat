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
	
	//�پ��� Ŭ���̾�Ʈ�� ���������� ��������� ȿ�������� ����        
	public static ExecutorService threadPool;
	//������ Ŭ���̾�Ʈ���� ����     ���ʹ� �迭
	public static Vector<Client> clients = new Vector<Client>();
	
	ServerSocket serverSocket;
	
	//������ �������Ѽ� Ŭ���̾�Ʈ�� ������ ��ٸ��� �޼ҵ��Դϴ�.
	//��� �����ǿ� ��Ʈ�� ��� Ŭ���̾�Ʈ�� ����� �Ұ��� ����
	public void startServer(String IP, int port)
	{
		try {
			//������ �����̵Ǹ� ������Ĺ���� �۾� ���ε��ؼ� ���� ��ǻ�� ������ �����ϴ� ��ǻ�Ͱ� �ڽ��� �������ּ� , ��Ʈ��ȣ��
			//Ư���� Ŭ���̾�Ʈ�� ������ ��ٸ����� �Ҽ����ִ�
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
		}catch(Exception e)
		{
			//�����ִ� ���°� �ƴ϶�� ��ž
			if(!serverSocket.isClosed())
			{
				stopServer();
			}
			return;
		}
		//������ �߻������ʰ� ���������� ������ ������ �߿�� ������ ��ٸ����ִ� ���°� �Ǿ��ٸ�
		//Ŭ���̾�Ʈ�� ������ ������ ��� ��ٸ��� �������Դϴ�.
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						//�����ؼ� �����̵Ǹ�
						Socket socket = serverSocket.accept();
						//Ŭ���̾�Ʈ�� ������ �ߴٸ� Ŭ���̾�Ʈ �迭�� ���Ӱ� ������ Ŭ���̾�Ʈ�� �߰�����
						clients.add(new Client(socket));
						System.out.println("[Ŭ���̾�Ʈ ����] "
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
					}catch(Exception e)
					{
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						//��������
						break;
					}
				}
			}
		};
		//������Ǯ�� �ʱ�ȭ
		threadPool = Executors.newCachedThreadPool();
		//���� Ŭ���̾�Ʈ�� ��ٸ��� �����带 �������ֵ��� ó���ؼ� ������ ���������� ���� �ʱ�ȭ ���ְ� �׾ȿ� ù��° ���� �����带 �־ ����
		threadPool.submit(thread);
	}
	//������ �۵��� ������Ű�� �޼ҵ�
	public void stopServer()
	{
		try {
			//���� �۵����� ��� ���� �ݱ�
			//Iterator��� Ŭ���̾�Ʈ�� ���������� �����Ҽ��ֵ��� �������
			Iterator<Client> iterator = clients.iterator();
			//Ŭ���̾�Ʈ�� �ϳ��� ������
			while(iterator.hasNext()) {
				//Ư���� Ŭ���̾�Ʈ�� �����ؼ�
				Client client = iterator.next();
				//�� Ŭ���̾�Ʈ�� ������ �ݴ´�
				client.socket.close();
				//iterator���� �ش� ���� Ŭ���̾�Ʈ�� ����
				iterator.remove();
			}
			//�������ϱ� ���� ���ϰ�ü �ݱ�
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			//������ Ǯ �����ϱ�
			if(threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//UI�� �����ϰ�, ���������� ���α׷��� ���۽�Ű�� �޼ҵ��Դϴ�.
	@Override
	//GUI�۾� ó��
	public void start(Stage primaryStage) {
		//��ü ������ Ʋ�� ���� �� �ִ� ��
		BorderPane root = new BorderPane();
		//���ο� 5��ŭ �е���
		root.setPadding(new Insets(5));
		
		//�乮�忡 �ؽ�Ʈ�� �����ֵ��� �ϴ� ����
		TextArea textArea = new TextArea();
		//������ �ܼ��ϰ� ��¸��ϰ� ������ �Ұ����ϰ�
		textArea.setEditable(false);
		//�۾�ü�� ��ġ�� �Ǿ��־��
		textArea.setFont(new Font("����", 15));
		root.setCenter(textArea);
		
		//������ �۵��� �����ϵ��� ������ִ� ��ư     toggleButton=����ġ
		Button toggleButton = new Button("�����ϱ�");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		//BorderPane�� ������ ��
		BorderPane.setMargin(toggleButton,new Insets(1, 0, 0, 0));
		root.setBottom(toggleButton);
		
		String IP = "127.0.0.1";
		int port = 9876;
		
		//toggleButton�������� �׼�ó�� event
		toggleButton.setOnAction(event ->{
			//�����ϱ⸦ �����ϰ��ִ� ���¶��
			if(toggleButton.getText().equals("�����ϱ�")) {
				//���� ����
				startServer(IP, port);
				//��ư�� �������� �ٷ� TextArea�� ��� ������ ������ �ϸ� �ȵǰ� �׻� runLater�Լ��� �̿��ؼ�
				//GUI��Ҹ� ����Ҽ��ֵ��� �ؾ���
				Platform.runLater(() ->{
					String message = String.format("[��������]\n",IP,port);
					//��μ� textArea�� �޽����� ����ϰԵ�
					textArea.appendText(message);
					//������ �����ϱ�� �ٲ�� �������
					toggleButton.setText("�����ϱ�");
				});
			}else
			{
				//�����ư�� �������� ����������
				stopServer();
				//GUL �� ȭ�鿡 ��� ������ ����ϵ��� ���
				Platform.runLater(() ->{
					String message = String.format("[��������]\n",IP,port);
					textArea.appendText(message);
					toggleButton.setText("�����ϱ�");
				});
			}
		});
		
		//ȭ��ũ��
		Scene scene = new Scene(root, 400, 400);
		//������
		primaryStage.setTitle("[ä�� ����]");
		//�����ߴٸ� stopServer�Լ��� �����ѵڿ� ����
		primaryStage.setOnCloseRequest(event -> stopServer());
		//scene������ ȭ�鿡 ���������� ����Ҽ��ֵ���
		primaryStage.setScene(scene);
		//show�� �̿��� ȭ�鿡 ���
		primaryStage.show();
	}
	
	//���α׷��� �������Դϴ�.
	
	public static void main(String[] args) {
		launch(args);
	}
}
