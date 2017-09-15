import javax.swing.JOptionPane;
public class Client
{
    public static void main(String[] args)  throws Exception
    {
        String inputMessage = "What would you like to do?\n" +
                "1 - Add User\n" +
                "2 - Remove User\n" +
                "3 - Check grid size\n" +
                "4 - Check game state\n" +
                "5 - Save score\n" +
                "6 - Exit";
        Network clientNetwork = new Network("228.5.6.7", 49514);
        String clientIP = clientNetwork.getIP();
        int clientPort = clientNetwork.getPort();
        System.out.println(clientNetwork.listen());
        String input;
        while(true)
        {
            input = JOptionPane.showInputDialog(inputMessage);
            if (input.equals("1"))
            {
                JOptionPane.showMessageDialog(null, "User added");
            }
            else if (input.equals("6"))
            {
                break;
            }
        }

    }
}
