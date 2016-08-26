package com.teamone.beautality.activities.help;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseFragment;

/**
 * Created by oshhepkov on 25.08.16.
 */
public class HelpFragment extends BaseFragment {
    private Button mBTSendMail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);
        mBTSendMail = (Button) root.findViewById(R.id.bt_send);
        mBTSendMail.setOnClickListener(mOnSendMailClickListener);
        return root;
    }

    View.OnClickListener mOnSendMailClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse("mailto:" + getString(R.string.developer_email))
                    .buildUpon()
                    .build();

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    };
}
