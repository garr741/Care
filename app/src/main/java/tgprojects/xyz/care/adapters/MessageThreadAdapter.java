package tgprojects.xyz.care.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.models.MessageItem;

public class MessageThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MY_MESSAGE = 0;
    public static final int YOUR_MESSAGE = 1;

    private List<MessageItem> messageThread;
    private MessageThreadListener listener;

    public MessageThreadAdapter(MessageThreadListener listener) {
        this.listener = listener;
        this.messageThread = getMessageThread();
    }

    public interface MessageThreadListener {
        void scrollView();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MY_MESSAGE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message_item, parent, false);
            return new MyMessageViewHolder(v);
        } else if (viewType == YOUR_MESSAGE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.their_message_item, parent, false);
            return new TheirMessageViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder aHolder, int position) {
        MessageItem messageItem = messageThread.get(position);
        if (aHolder instanceof MyMessageViewHolder) {
            MyMessageViewHolder holder = (MyMessageViewHolder) aHolder;
            holder.message.setText(messageItem.getMessage());
        } else {
            TheirMessageViewHolder holder = (TheirMessageViewHolder) aHolder;
            holder.message.setText(messageItem.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageThread.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageItem messageItem = messageThread.get(position);
        return messageItem.isMine() ? MY_MESSAGE : YOUR_MESSAGE;
    }

    public void addMessage(String value) {
        messageThread.add(new MessageItem(true, value));
        notifyDataSetChanged();
        listener.scrollView();
    }

    public final class MyMessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.my_message_text) TextView message;

        public MyMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public final class TheirMessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.their_message_text)
        TextView message;

        public TheirMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static List<MessageItem> getMessageThread() {
        List<MessageItem> messagesThread = new ArrayList<>();
        messagesThread.add(new MessageItem(true, "In Lorem fugiat cupidatat aliqua incididunt sint duis consequat incididunt veniam eiusmod irure culpa."));
        messagesThread.add(new MessageItem(true, "Qui ipsum qui nisi cillum laborum ullamco qui."));
        messagesThread.add(new MessageItem(false, "Ad ea voluptate do qui minim qui sint et ex cillum enim dolor."));
        messagesThread.add(new MessageItem(true, "Consectetur duis reprehenderit ea qui commodo esse nulla eiusmod proident dolor nulla."));
        messagesThread.add(new MessageItem(getRandomBoolean(), "Exercitation culpa esse quis cupidatat deserunt sint ex occaecat."));
        messagesThread.add(new MessageItem(getRandomBoolean(), "Elit exercitation pariatur mollit voluptate deserunt aute proident enim consequat tempor id deserunt."));
        messagesThread.add(new MessageItem(false, "Adipisicing est non proident aliquip labore reprehenderit excepteur aliqua et enim. Ea nulla ipsum commodo fugiat elit cillum in."));
        messagesThread.add(new MessageItem(false, "Velit sit minim voluptate eu ut mollit duis amet officia laborum. Qui voluptate sunt mollit dolor proident consectetur labore ullamco. Aliqua incididunt occaecat veniam et aliquip id ullamco aliquip pariatur laboris ex ipsum mollit."));
        messagesThread.add(new MessageItem(true, "Sit consequat occaecat eu sit duis est aute consectetur ullamco proident duis do anim. Veniam consequat commodo do velit anim proident nisi consectetur. "));
        messagesThread.add(new MessageItem(false, "Voluptate est laborum adipisicing commodo dolore sunt minim do est. Minim cillum amet proident irure exercitation amet eu mollit nostrud sunt nisi ipsum quis ea. Ullamco dolor incididunt dolor laboris in exercitation Lorem ullamco incididunt enim velit"));
        messagesThread.add(new MessageItem(true, "Nostrud voluptate pariatur laborum esse aliqua commodo reprehenderit ut reprehenderit culpa."));
        messagesThread.add(new MessageItem(getRandomBoolean(), "Velit sit minim voluptate eu ut mollit duis amet officia laborum."));
        return messagesThread;
    }

    private static boolean getRandomBoolean() {
        int num = new Random().nextInt(10);
        return num % 2 == 0;
    }
}
