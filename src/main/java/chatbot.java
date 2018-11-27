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

            else if ((message_text).equals("@joinGame")){
                player_count += 1;
                SendMessage message = new SendMessage().setChatId(session_id).setText("You have successfully entered the game");

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(player_count);
        if(player_count == 3)
            System.exit(1);
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
