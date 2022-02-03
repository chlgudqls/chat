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
	// 클라이언트로부터 메시지를 전달 받는 메소드입니다.
	public void receive()
	{
		//하나의 스레드를 만들때 Runnable사용
		Runnable thread = new Runnable() {
			//하나의 스레드가 어떠한 모듈로써 동작을 할건지 런안에서 정의
			@Override
			public void run() {
				try {
					//화일을 넣어서 클라이언트로부터 어떠한 내용을 반복적으로 전달받을수있게함
					while(true) {
						//InputStream전달받음
						InputStream in = socket.getInputStream();
						//한번에 512바이트만큼 전달받을수있도록
						byte[] buffer = new byte[512];
						//클라이언트로부터 전달받아서 버퍼에 담음 
						int length = in.read(buffer);
						//렝쓰는 담긴 크기 오류가 발생했다면 
						while(length == -1)throw new IOException();
						System.out.println("[메시지 수신 성공) "
								//현재 접속한 클라이언트의 아이피주소 같은 주소정보를 출력
							+ socket.getRemoteSocketAddress()
							//스레드의 고유한 정보 출력 (스레드 이름값)
							+ ": " + Thread.currentThread().getName());
						//버퍼에서 전달받은내용을 메시지라는 이름의 문자열 변수에 담아서 출력하게   (한글 포함 인코딩처리)
						String message = new String(buffer, 0, length, "UTF-8");
						//클라이언트한테 전달받은 메시지를 다른 클라이언트들한테도 보냄 
						for(Client client : Main.clients) {
							client.send(message);
							System.out.println(message);
						}
					}
				}catch(Exception e)
				{
					try {
						System.out.println("[메시지 수신 오류] "
								//메시지를 보낸 클라이언트의 주소를 출력
								+ socket.getRemoteSocketAddress()
								//해당스레드 고유이름 출력
								+ ": " + Thread.currentThread().getName()); 
					}catch(Exception e2)
					{
						e.printStackTrace();
					}
				}
			}
		};
		//스레드풀에 만들어진 하나의 스레드를 등록시킴
		Main.threadPool.submit(thread);
	}
	//클라이언트에게 메시지를 전송하는 메소드입니다.
	public void send(String message)
	{
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					//오류가 발생하지 않았을때는 버퍼에 담긴 내용을 서버에서 클라이언트로 전송
					out.write(buffer);
					//여기까지 전송완료했다는걸 알려줌
					out.flush();
				}catch(Exception e)
				{
					//전달 받는 도중에 오류가 발생했다면
					try {
						System.out.println("[메시지 송신 오류]"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						//오류 발생시 모든 클라이언트들에대한 정보를 담는 배열에서 현재존재하는 클라이언트를 지움 
						//서버로부터 접속이 끊겻으면 처리 클라이언트 배열에서 오류가생긴 해당 클라이언트를 제거
						Main.clients.remove(Client.this);
						//오류가생긴 클라이언트의 소켓을 닫아버림
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
