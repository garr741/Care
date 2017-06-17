package tgprojects.xyz.care.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import tgprojects.xyz.care.DTO.Resource;
import tgprojects.xyz.care.R;

/**
 * Created by mvieck on 6/17/17.
 */

public class ResourcesAdapter extends BaseAdapter {

    private List<Resource> resources;
    private Context context;

    public ResourcesAdapter(List<Resource> resources, Context context) {
        this.resources = resources;
        this.context = context;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return resources.size();
    }

    @Override
    public Resource getItem(int i) {
        return resources.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Resource resource = getItem(i);

        return createResourceView(resource);
    }

    private View createResourceView(final Resource resource) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.resource_view, null);
        view.findViewById(R.id.resource_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(resource.getUrl()));
                context.startActivity(browserIntent);
            }
        });
        TextView title = (TextView) view.findViewById(R.id.text_resource_title);
        title.setText(resource.getTitle());
        TextView description = (TextView) view.findViewById(R.id.text_resource_description);
        description.setText(resource.getDescription());
        return view;
    }
}
