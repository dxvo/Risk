import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//730204765:AAHqIUzXasdRJsZUDySybOi_yX4BBZOtT58

/***
 *This chatbot class extends TelegramLongPollingBot class under Telegram API
 * This class gives players the ability to start the game from chatBot.
 * The default number of player is set to 3. Once the chatBot has 3 players join - the game is automatically started
 * if starting the game by this method, the game initial conditions are intilized with 3 players
 * @author De Vo
 * @version 1.1
 * @since 2018-11-20
 * @see <a href="https://core.telegram.org/bots/samples">https://core.telegram.org/bots/samples</a>
 */
public class chatbot extends TelegramLongPollingBot {

    int player_count = 0;
    long[] player_ID = new long[3];

    @Override
    /***
     * This method is to check the chatBot content
     * The player can send @risk_dwc to ask to join the game
     * When chatbot receives request to join game from user, it replies back with a message
     * The play then reply @joinGame to join the game.
     * Chatbot keeps tracks of the number of players who ask to join the game
     * The game starts when the number of player is 3.
     * Chatbot also keeps track of player chatID. Only user with different chat ID is added toward player list
     */
    public void onUpdateReceived(Update update) {


        if (update.hasMessage() && update.getMessage().hasText())
        {

            String message_text = update.getMessage().getText();
            long session_id = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getFirstName();


            if ((message_text).equals("@risk_dwc")) {
                SendMessage message = new SendMessage()
                        .setChatId(session_id).setText("Welcome to Risk Game. The game needs 3 players to start. " +
                                "Please enter @joinGame to join game session");
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            else if ((message_text).equals("@joinGame"))
            {
                Boolean flag = false;
                for(int i = 0; i < 3; i++)
                {
                    if(session_id == player_ID[i])
                        flag = true;
                }
                if(!flag)
                {
                    player_ID[player_count] = session_id;
                    player_count += 1;
                    flag = false;
                }

                String answer = "You have successfully entered the game." + "You are currently player #" + Integer.toString(player_count);
                SendMessage message = new SendMessage().setChatId(session_id).setText(answer);

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
        //System.out.println(player_count);

        if(player_count == 3){
            String game_start_message = "3 players has joined. The game is now started";
            for(int i = 0; i < 3; i++){
                SendMessage message = new SendMessage().setChatId(player_ID[i]).setText(game_start_message);
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            GameMaster gameMaster = new GameMaster(player_count);
            gameMaster.gameStart();

        }

    }

    @Override
    /***
     * This method returns the chatBot ID
     */
    public String getBotUsername() {
        // TODO
        return "dwc_risk_bot";
    }

    @Override
    /***
     * this method is key token
     */
    public String getBotToken() {
        // TODO
        return "730204765";
    }
}
