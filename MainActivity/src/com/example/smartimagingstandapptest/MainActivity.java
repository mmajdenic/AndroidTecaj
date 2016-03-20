/*
Copyright (c) 2013, Sony Mobile Communications AB

All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

 * Neither the name of the Sony Mobile Communications AB nor the names
  of its contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.example.smartimagingstandapptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sonymobile.smartconnect.motion.ConnectionState;
import com.sonymobile.smartconnect.motion.SmartImagingStandEventListener;
import com.sonymobile.smartconnect.motion.SmartImagingStandManager;
import com.sonymobile.smartconnect.motion.SpeedType;
import com.sonymobile.smartconnect.motion.Direction;

public class MainActivity extends Activity implements SmartImagingStandEventListener {

    private RadioButton mRadioMid;
    private RadioButton mRadioFast;
    private RadioButton mRadioVeryFast;

    private boolean mUntilStop = false;
    private boolean mShowPosition = false;

    /** Reference to the Smart Imaging Stand manager. */
    private SmartImagingStandManager mSISManager;

    @Override
    protected void onPause() {
        mSISManager.unbind();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mSISManager.bind();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioMid = (RadioButton) findViewById(R.id.radioMid);
        mRadioFast = (RadioButton) findViewById(R.id.radioFast);
        mRadioVeryFast = (RadioButton) findViewById(R.id.radioVeryFast);

        /**
         * Creates a new instance of the manager and bind to the running service
         * of the Motion API, provided by Smart Imaging Stand Host Application.
         */
        mSISManager = new SmartImagingStandManager(this, this);

        /** A ClickListener for move buttons. */
        OnClickListener directionClickListener = new OnClickListener() {
            public void onClick(View v) {
                int direction = Direction.STOP;

                switch (v.getId()) {
                    case R.id.buttonLeftUp:
                        direction = Direction.LEFT_UP;
                        break;
                    case R.id.buttonUp:
                        direction = Direction.UP;
                        break;
                    case R.id.buttonRightUp:
                        direction = Direction.RIGHT_UP;
                        break;
                    case R.id.buttonLeft:
                        direction = Direction.LEFT;
                        break;
                    case R.id.buttonStop:
                        direction = Direction.STOP;
                        break;
                    case R.id.buttonRight:
                        direction = Direction.RIGHT;
                        break;
                    case R.id.buttonLeftDown:
                        direction = Direction.LEFT_DOWN;
                        break;
                    case R.id.buttonDown:
                        direction = Direction.DOWN;
                        break;
                    case R.id.buttonRightDown:
                        direction = Direction.RIGHT_DOWN;
                        break;
                }

                if (direction == Direction.STOP) {
                    mSISManager.stop();
                } else {
                    if (mUntilStop) {
                        mSISManager.moveUntilStop(direction, getSpeedType());
                    } else {
                        mSISManager.move(direction, getSpeedType());
                    }
                }
            }
        };

        /** Buttons for direction. */
        final Button buttonLeftUp = (Button) findViewById(R.id.buttonLeftUp);
        final Button buttonUp = (Button) findViewById(R.id.buttonUp);
        final Button buttonRightUp = (Button) findViewById(R.id.buttonRightUp);

        final Button buttonLeft = (Button) findViewById(R.id.buttonLeft);
        final Button buttonStop = (Button) findViewById(R.id.buttonStop);
        final Button buttonRight = (Button) findViewById(R.id.buttonRight);

        final Button buttonLeftDown = (Button) findViewById(R.id.buttonLeftDown);
        final Button buttonDown = (Button) findViewById(R.id.buttonDown);
        final Button buttonRightDown = (Button) findViewById(R.id.buttonRightDown);

        /** Buttons for getting hardware info. */
        final Button buttonFirmware = (Button) findViewById(R.id.buttonFirmware);
        final Button buttonReset = (Button) findViewById(R.id.buttonReset);
        final Button buttonPosition = (Button) findViewById(R.id.buttonPosition);
        final Button buttonSpeed = (Button) findViewById(R.id.buttonSpeed);
        final Button buttonConnectionState = (Button) findViewById(R.id.buttonConnectionState);

        /** Some moves for tests purposes. */
        final Button buttonYes = (Button) findViewById(R.id.buttonYes);
        final Button buttonNo = (Button) findViewById(R.id.buttonNo);
        final Button buttonAutoMove = (Button) findViewById(R.id.buttonAutoMove);

        /** Controls for setting the speed of the hardware. */
        final EditText panSpeed = (EditText) findViewById(R.id.panSpeed);
        final EditText tiltSpeed = (EditText) findViewById(R.id.tiltSpeed);
        final Button buttonSetSpeed = (Button) findViewById(R.id.buttonSetSpeed);

        buttonSetSpeed.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int panSpd = 0;
                int tiltSpd = 0;

                try {
                    panSpd = Integer.valueOf(panSpeed.getText().toString());
                    tiltSpd = Integer.valueOf(tiltSpeed.getText().toString());
                } catch (NumberFormatException e) {
                    // Cannot convert input to Integer.
                }

                // Updates the speed of the hardware.
                mSISManager.setSpeed(panSpd <= 5 ? panSpd = 5 : panSpd, tiltSpd <= 3 ? tiltSpd = 3
                        : tiltSpd, getSpeedType());

                panSpeed.setText(String.valueOf(panSpd));
                tiltSpeed.setText(String.valueOf(tiltSpd));
            };
        });

        buttonLeftUp.setOnClickListener(directionClickListener);
        buttonUp.setOnClickListener(directionClickListener);
        buttonRightUp.setOnClickListener(directionClickListener);

        buttonLeft.setOnClickListener(directionClickListener);
        buttonStop.setOnClickListener(directionClickListener);
        buttonRight.setOnClickListener(directionClickListener);

        buttonLeftDown.setOnClickListener(directionClickListener);
        buttonDown.setOnClickListener(directionClickListener);
        buttonRightDown.setOnClickListener(directionClickListener);

        buttonReset.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mSISManager.reset();
            }
        });

        buttonFirmware.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mSISManager.getFirmwareVersion();
            }
        });

        buttonPosition.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mSISManager.getCurrentPosition();
                mShowPosition = true;
            }
        });

        buttonSpeed.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mSISManager.getCurrentSpeed(getSpeedType());
            }
        });

        buttonConnectionState.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mSISManager.getConnectionState();
            }
        });

        buttonYes.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                makeYes();
            }
        });

        buttonNo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                makeNo();
            }
        });

        buttonAutoMove.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                execAutoMoveSeq();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_untilStop:
                if (checked)
                    mUntilStop = true;
                else
                    mUntilStop = false;
                break;
            default:
        }
    }

    private void makeYes() {
        int[][] moveSequence = {
                {
                        Direction.DOWN, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.UP, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.DOWN, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.UP, SpeedType.VERY_FAST, 250
                }
        };
        new execCommand(moveSequence).start();
    }

    private void makeNo() {
        int[][] moveSequence = {
                {
                        Direction.RIGHT, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.LEFT, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.RIGHT, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.LEFT, SpeedType.VERY_FAST, 250
                }
        };
        new execCommand(moveSequence).start();
    }

    private void execAutoMoveSeq() {
        int[][] moveSequence = {
                {
                        Direction.LEFT, SpeedType.VERY_FAST, 2000
                },
                {
                        Direction.RIGHT, SpeedType.VERY_FAST, 2000
                },
                {
                        Direction.UP, SpeedType.VERY_FAST, 1000
                },
                {
                        Direction.DOWN, SpeedType.VERY_FAST, 1000
                },
                {
                        Direction.LEFT_UP, SpeedType.VERY_FAST, 1000
                },
                {
                        Direction.RIGHT_DOWN, SpeedType.VERY_FAST, 1000
                },
                {
                        Direction.UP, SpeedType.VERY_FAST, 500
                },
                {
                        Direction.LEFT, SpeedType.VERY_FAST, 2000
                },
                {
                        Direction.RIGHT, SpeedType.VERY_FAST, 2000
                }
        };
        new execCommand(moveSequence).start();
    }

    class execCommand extends Thread {
        int[][] mMoveSequence = null;;

        int currentPosition = 0;

        public execCommand(int[][] moveSequence) {
            mMoveSequence = moveSequence;
        }

        public void run() {
            if (mMoveSequence != null) {
                while (currentPosition < mMoveSequence.length) {
                    mSISManager.move(mMoveSequence[currentPosition][0],
                            mMoveSequence[currentPosition][1], mMoveSequence[currentPosition][2]);
                    if (mMoveSequence[currentPosition][2] > 0) {
                        try {
                            Thread.sleep(mMoveSequence[currentPosition][2]);
                        } catch (InterruptedException e) {
                        }
                    }
                    currentPosition++;
                }
            }
        }
    }

    public void onBatteryLow() {
        Toast.makeText(this, R.string.batteryLow, Toast.LENGTH_SHORT).show();
    }

    public void onBatteryEmpty() {
        Toast.makeText(this, R.string.batteryEmpty, Toast.LENGTH_SHORT).show();
    }

    public void onMechanicalError() {
        Toast.makeText(this, R.string.mechanicalError, Toast.LENGTH_SHORT).show();
    }

    public void onMenuButtonPressed() {
        Toast.makeText(this, R.string.menuButton, Toast.LENGTH_SHORT).show();
    }

    public void onOperationButtonPressed() {
        Toast.makeText(this, R.string.operationButton, Toast.LENGTH_SHORT).show();
    }

    public void onCurrentSpeed(int panSpeed, int tiltSpeed, String speedType) {
        Toast.makeText(this, getString(R.string.currentSpeed, speedType, panSpeed, tiltSpeed),
                Toast.LENGTH_SHORT).show();
    }

    public void onFirmwareVersion(String firmwareVersion) {
        Toast.makeText(this, firmwareVersion, Toast.LENGTH_SHORT).show();
    }

    public void onCurrentPosition(int panPosition, int tiltPosition) {
        if (mShowPosition) {
            Toast.makeText(this, getString(R.string.currentPosition, panPosition, tiltPosition),
                    Toast.LENGTH_SHORT)
                    .show();
            mShowPosition = false;
        }
    }

    private int getSpeedType() {
        int speedType = SpeedType.SLOW;

        if (mRadioMid.isChecked()) {
            speedType = SpeedType.MID;
        } else if (mRadioFast.isChecked()) {
            speedType = SpeedType.FAST;
        } else if (mRadioVeryFast.isChecked()) {
            speedType = SpeedType.VERY_FAST;
        }

        return speedType;
    }

    public void onDeviceConnected() {
        Toast.makeText(this, R.string.onDeviceConnected, Toast.LENGTH_SHORT).show();
    }

    public void onDeviceDisconnected() {
        Toast.makeText(this, R.string.onDeviceDisconnected, Toast.LENGTH_SHORT).show();
    }

    public void onConnectionState(int connState) {
        Toast.makeText(
                this,
                getString(R.string.connectionState, connState, ConnectionState.STATE_NONE,
                        ConnectionState.STATE_CONNECTING, ConnectionState.STATE_CONNECTED,
                        ConnectionState.STATE_CONNECTION_LOST), Toast.LENGTH_SHORT).show();
    }

    public void onConnectionFailed() {
        Toast.makeText(this, R.string.robotUnavailable, Toast.LENGTH_SHORT).show();
    }
}
