package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client 
{
	Socket socket;
	
	public Client(Socket socket)
	{
		this.socket = socket;
		receive();
	}
	// Ŭ���̾�Ʈ�κ��� �޽����� ���� �޴� �޼ҵ��Դϴ�.
	public void receive()
	{
		//�ϳ��� �����带 ���鶧 Runnable���
		Runnable thread = new Runnable() {
			//�ϳ��� �����尡 ��� ���ν� ������ �Ұ��� ���ȿ��� ����
			@Override
			public void run() {
				try {
					//ȭ���� �־ Ŭ���̾�Ʈ�κ��� ��� ������ �ݺ������� ���޹������ְ���
					while(true) {
						//InputStream���޹���
						InputStream in = socket.getInputStream();
						//�ѹ��� 512����Ʈ��ŭ ���޹������ֵ���
						byte[] buffer = new byte[512];
						//Ŭ���̾�Ʈ�κ��� ���޹޾Ƽ� ���ۿ� ���� 
						int length = in.read(buffer);
						//������ ��� ũ�� ������ �߻��ߴٸ� 
						while(length == -1)throw new IOException();
						System.out.println("[�޽��� ���� ����) "
								//���� ������ Ŭ���̾�Ʈ�� �������ּ� ���� �ּ������� ���
							+ socket.getRemoteSocketAddress()
							//�������� ������ ���� ��� (������ �̸���)
							+ ": " + Thread.currentThread().getName());
						//���ۿ��� ���޹��������� �޽������ �̸��� ���ڿ� ������ ��Ƽ� ����ϰ�   (�ѱ� ���� ���ڵ�ó��)
						String message = new String(buffer, 0, length, "UTF-8");
						//Ŭ���̾�Ʈ���� ���޹��� �޽����� �ٸ� Ŭ���̾�Ʈ�����׵� ���� 
						for(Client client : Main.clients) {
							client.send(message);
							System.out.println(message);
						}
					}
				}catch(Exception e)
				{
					try {
						System.out.println("[�޽��� ���� ����] "
								//�޽����� ���� Ŭ���̾�Ʈ�� �ּҸ� ���
								+ socket.getRemoteSocketAddress()
								//�ش罺���� �����̸� ���
								+ ": " + Thread.currentThread().getName()); 
					}catch(Exception e2)
					{
						e.printStackTrace();
					}
				}
			}
		};
		//������Ǯ�� ������� �ϳ��� �����带 ��Ͻ�Ŵ
		Main.threadPool.submit(thread);
	}
	//Ŭ���̾�Ʈ���� �޽����� �����ϴ� �޼ҵ��Դϴ�.
	public void send(String message)
	{
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					//������ �߻����� �ʾ������� ���ۿ� ��� ������ �������� Ŭ���̾�Ʈ�� ����
					out.write(buffer);
					//������� ���ۿϷ��ߴٴ°� �˷���
					out.flush();
				}catch(Exception e)
				{
					//���� �޴� ���߿� ������ �߻��ߴٸ�
					try {
						System.out.println("[�޽��� �۽� ����]"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						//���� �߻��� ��� Ŭ���̾�Ʈ�鿡���� ������ ��� �迭���� ���������ϴ� Ŭ���̾�Ʈ�� ���� 
						//�����κ��� ������ �������� ó�� Ŭ���̾�Ʈ �迭���� ���������� �ش� Ŭ���̾�Ʈ�� ����
						Main.clients.remove(Client.this);
						//���������� Ŭ���̾�Ʈ�� ������ �ݾƹ���
						socket.close();
					}catch(Exception e2)
					{
						e2.printStackTrace();					
					}
				}
			}
		};
		Main.threadPool.submit(thread);
	}
}
