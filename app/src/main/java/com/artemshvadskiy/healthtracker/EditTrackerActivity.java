package com.artemshvadskiy.healthtracker;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.artemshvadskiy.healthtracker.db.PrimaryKey;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

public class EditTrackerActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "id";

    private Realm mRealm;

    //private Tracker mTracker;

    public static void startActivity(Activity activity, Long id) {
        Intent intent = new Intent(activity, EditTrackerActivity.class);
        intent.putExtra(EXTRA_ID, id);
        activity.startActivity(intent);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getDefaultInstance();

        setContentView(R.layout.activity_edit_tracker);

        final TextInputEditText nameEditTextView = (TextInputEditText) findViewById(R.id.name);
        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        if (id != -1) {
            mTracker = mRealm.where(Tracker.class).equalTo(PrimaryKey.ID, id).findFirst();
            nameEditTextView.setText(mTracker.mName);
        } else {
            mTracker = new Tracker();
        }

        RecyclerView alarmList = (RecyclerView) findViewById(R.id.alarm_list);
        alarmList.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        alarmList.setAdapter(new AlarmRecyclerAdapter(mTracker.mAlarms));

        findViewById(R.id.add_alarm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(EditTrackerActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, final int hourOfDay, final int minute) {
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                Alarm alarm = mRealm.createObject(Alarm.class,
                                        PrimaryKey.generateKey(Alarm.class));
                                alarm.mHour = hourOfDay;
                                alarm.mMinute = minute;
                                mTracker.mAlarms.add(alarm);
                            }
                        });
                    }
                }, 0, 0, false).show();
            }
        });

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameEditTextView.getText().toString())) {
                    Toast.makeText(EditTrackerActivity.this, "Name is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        mTracker.mName = nameEditTextView.getText().toString();
                        if (!mTracker.isValid()) {
                            mTracker.mId = PrimaryKey.generateKey(Tracker.class);
                            realm.copyToRealm(mTracker);
                        }
                    }
                });
                finish();
            }
        });

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mRealm.close();

        super.onDestroy();
    }

    private class AlarmRecyclerAdapter
            extends RealmRecyclerViewAdapter<Alarm, AlarmRecyclerAdapter.ViewHolder> {

        public AlarmRecyclerAdapter(OrderedRealmCollection<Alarm> alarms) {
            super(EditTrackerActivity.this, alarms, true);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.alarm_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Alarm alarm = getData().get(position);
            holder.mAlarm = alarm;
            holder.mTime.setText(alarm.mHour + ":" + alarm.mMinute);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private Alarm mAlarm;

            private TextView mTime;
            private ImageButton mEditButton;
            private ImageButton mDeleteButton;

            public ViewHolder(View rootView) {
                super(rootView);

                mTime = (TextView) rootView.findViewById(R.id.time);
                mEditButton = (ImageButton) rootView.findViewById(R.id.edit_button);
                mDeleteButton = (ImageButton) rootView.findViewById(R.id.delete_button);

                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new TimePickerDialog(EditTrackerActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, final int hourOfDay, final int minute) {
                                mRealm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        mAlarm.mHour = hourOfDay;
                                        mAlarm.mMinute = minute;
                                    }
                                });
                            }
                        }, mAlarm.mHour, mAlarm.mMinute, false).show();
                    }
                });

                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                //mTracker.mAlarms.remove(mAlarm);
                                mAlarm.deleteFromRealm();
                            }
                        });
                    }
                });
            }
        }
    }*/
}
