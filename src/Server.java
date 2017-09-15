public class Server
{
    public static void main(String[] args) throws Exception
    {
        Network serverNetwork = new Network("228.5.6.7",49514);
        serverNetwork.broadcast(serverNetwork.getIP() + " " + serverNetwork.getPort());
    }
}
