import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Network
{
    private DatagramSocket direct;
    private MulticastSocket multicast;
    private int multicastPort;
    private InetAddress multicastIP;

    Network(String multicastIP, int multicastPort) throws Exception
    {
        this.multicastPort = multicastPort;
        this.multicastIP = InetAddress.getByName(multicastIP);
        direct = new DatagramSocket();
        multicast = new MulticastSocket(multicastPort);
        multicast.joinGroup(this.multicastIP);
    }

    public void close() throws Exception
    {
        multicast.leaveGroup(multicastIP);
        multicast.close();
        direct.close();
    }

    public void send(String targetIP, int targetPort, String message) throws Exception
    {
        InetAddress address = InetAddress.getByName(targetIP);
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), address, targetPort);
        direct.send(packet);
    }

    public String read() throws Exception
    {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        direct.receive(packet);

        return new String(buffer).trim();
    }

    public String getIP() throws Exception
    {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public int getPort() throws Exception
    {
        return direct.getLocalPort();
    }

    public void broadcast(String message) throws Exception
    {
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), multicastIP, multicastPort);
        multicast.send(packet);
    }

    public String listen() throws Exception
    {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        multicast.receive(packet);

        return new String(buffer).trim();
    }
}
