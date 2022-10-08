package com.NGApps.NGScratch.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import com.NGApps.NGScratch.R;
import com.NGApps.NGScratch.fragments.ContactFragment;
import com.NGApps.NGScratch.fragments.ForgotFragment;
import com.NGApps.NGScratch.fragments.GoldFragment;
import com.NGApps.NGScratch.fragments.LeaderBoardFragment;
import com.NGApps.NGScratch.fragments.PlatinumFragment;
import com.NGApps.NGScratch.fragments.ProfileFragment;
import com.NGApps.NGScratch.fragments.ReferFragment;
import com.NGApps.NGScratch.fragments.SilverFragment;
import com.NGApps.NGScratch.fragments.WalletFragment;
import com.NGApps.NGScratch.utils.Constant;

public class ReferActivity extends AppCompatActivity {

    private String type;
    private ReferActivity activity;
    private Fragment fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        activity = this;
        type = getIntent().getStringExtra("type");

        if (type != null) {
            switch (type) {
                case "changePassword":
                    fm = ForgotFragment.newInstance();
                    break;

                case "wallet":
                    fm = WalletFragment.newInstance();
                    break;
                case "contact":
                    fm = ContactFragment.newInstance();
                    break;
                case "Profile":
                    fm = ProfileFragment.newInstance();
                    break;
                case "refer":
                    fm = ReferFragment.newInstance();
                    break;
                case "Silver Scratch":
                    fm = SilverFragment.newInstance();
                    break;
                case "Platinum Scratch":
                    fm = PlatinumFragment.newInstance();
                    break;
                case "Gold Scratch":
                    fm = GoldFragment.newInstance();
                    break;
                case "LeaderBoard":
                    fm = LeaderBoardFragment.newInstance();
                    break;

            }
            if (fm != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_refer, fm).commit();
            }
        } else {
            Constant.showToastMessage(activity, "Something Went Wrong...");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}