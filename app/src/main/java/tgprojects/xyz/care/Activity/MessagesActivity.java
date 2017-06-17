package tgprojects.xyz.care.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.adapters.MessageThreadAdapter;

public class MessagesActivity extends AppCompatActivity implements MessageThreadAdapter.MessageThreadListener {


    private MessageThreadAdapter adapter;

    @BindView(R.id.message_thread_recycler_view) RecyclerView recyclerView;

    @BindView(R.id.message_input) EditText messageInput;

    @BindView(R.id.send_message) ImageButton sendMessage;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageThreadAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, final int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if ( bottom < oldBottom) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.smoothScrollToPosition(bottom);
                        }
                    }, 100);
                }
            }
        });
    }

    @Override public void scrollView() {
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    @OnClick(R.id.send_message) void onSendMessageClicked() {
        if (!messageInput.getText().toString().isEmpty()) {
            adapter.addMessage(messageInput.getText().toString());
            messageInput.setText("");
        }
    }
}
