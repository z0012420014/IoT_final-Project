package leon.iot_project.custom_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import leon.iot_project.R;
import leon.iot_project.custom.TrashCan;

/**
 * Created by Leon on 2017/12/14.
 */

public class TrashCanAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<TrashCan> trashCanList;

    private class ViewHolder {
        TextView id_textView;
        TextView full_textView;
        TextView time_textView;
        public ViewHolder(TextView id_textView, TextView full_textView, TextView time_textView){
            this.id_textView = id_textView;
            this.full_textView = full_textView;
            this.time_textView = time_textView;
        }
    }

    public TrashCanAdapter(Context context, List<TrashCan> trashCanList){
        myInflater = LayoutInflater.from(context);
        this.trashCanList = trashCanList;
    }

    @Override
    public int getCount() {
        return trashCanList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return trashCanList.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return trashCanList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.trashcan_id),
                    (TextView) convertView.findViewById(R.id.full),
                    (TextView) convertView.findViewById(R.id.update_time)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.id_textView.setText(trashCanList.get(0).getTime());

        TrashCan trashCan = (TrashCan)getItem(position);
        holder.id_textView.setText(String.valueOf(trashCan.getId()));
        holder.time_textView.setText(String.valueOf(trashCan.getTime()));
        holder.full_textView.setText(String.valueOf(trashCan.getFull()) + "%");
        if(trashCan.getFull() > 80) {
            holder.full_textView.setTextColor(Color.RED);
        }else{
            holder.full_textView.setTextColor(Color.WHITE);
        }

        return convertView;
    }
}
