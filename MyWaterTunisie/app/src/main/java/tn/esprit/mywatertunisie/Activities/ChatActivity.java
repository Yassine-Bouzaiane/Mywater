package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import tn.esprit.mywatertunisie.Entities.ChatMessage;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;

public class ChatActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;

    //Add Emojicon
    EmojiconEditText emojiconEditText;
    ImageView emojiButton, submitButton;
    EmojIconActions emojIconActions;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
//            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    Snackbar.make(activity_main,"You have been signed out.", Snackbar.LENGTH_SHORT).show();
//                    finish();
//                }
//            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(activity_main, "Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            } else {
                Snackbar.make(activity_main, "We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (RelativeLayout) findViewById(R.id.activity_main);

        //Add Emoji
        emojiButton = (ImageView) findViewById(R.id.emoji_button);
        submitButton = (ImageView) findViewById(R.id.submit_button);
        emojiconEditText = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);
        emojIconActions = new EmojIconActions(getApplicationContext(), activity_main, emojiconEditText, emojiButton);
        emojIconActions.ShowEmojIcon();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(emojiconEditText.getText().toString(),
//                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
//                emojiconEditText.setText("");
//                emojiconEditText.requestFocus();
            }
        });

        //Check if not sign-in then navigate Signin page
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        } else {
            Snackbar.make(activity_main, "Welcome " + SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail(), Snackbar.LENGTH_SHORT).show();
            //Load content
            displayChatMessage();
        }


//        if(FirebaseAuth.getInstance().getCurrentUser() == null)
//        {
//            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
//        }
//        else
//        {
//            Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
//            //Load content
//            displayChatMessage();
//        }


    }


    private void displayChatMessage() {

        ListView listOfMessage = (ListView) findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.chat_list_item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                //Get references to the views of chat_list_item.xml
                TextView messageText, messageUser, messageTime;
                messageText = (BubbleTextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

            }
        };
        listOfMessage.setAdapter(adapter);
    }
}
