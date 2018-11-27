import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//730204765:AAHqIUzXasdRJsZUDySybOi_yX4BBZOtT58
// https://telegram.me/dwc_risk_bot?game=Risk
import java.util.*;


public class chatbot extends TelegramLongPollingBot {

    int player_count = 0;
    long[] player_ID = new long[3];

    @Override
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
    public String getBotUsername() {
        // TODO
        return "dwc_risk_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "730204765:AAHqIUzXasdRJsZUDySybOi_yX4BBZOtT58";
    }
}
