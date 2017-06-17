package tgprojects.xyz.care.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.List;
import tgprojects.xyz.care.R;
import tgprojects.xyz.care.models.ImportantInfo;

public class ImportantInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY_VIEW = 0;
    private static final int INFO_VIEW = 1;

    private List<ImportantInfo> importantInfos;
    private Context context;

    public ImportantInfoAdapter(Context context, List<ImportantInfo> importantInfos) {
        this.context = context;
        this.importantInfos = importantInfos;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == EMPTY_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            return new EmptyView(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.important_info_card, parent, false);
            return new InfoView(v);
        }
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoView) {
            InfoView infoView = (InfoView) holder;
            ImportantInfo info = importantInfos.get(position);
            Picasso.with(context).load(info.getImage()).into(infoView.imageView);
            infoView.title.setText(info.getTitle());
        }
    }

    @Override public int getItemCount() {
        return importantInfos.size();
    }

    @Override public int getItemViewType(int position) {
        return getItemCount() > 0 ? INFO_VIEW : EMPTY_VIEW;
    }

    public final class EmptyView extends RecyclerView.ViewHolder {
        public EmptyView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public final class InfoView extends RecyclerView.ViewHolder {

        @BindView(R.id.important_info_card_image) ImageView imageView;
        @BindView(R.id.important_info_card_title) TextView title;

        public InfoView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
