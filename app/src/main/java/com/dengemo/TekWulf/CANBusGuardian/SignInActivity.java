package com.dengemo.TekWulf.CANBusGuardian;

import static android.text.Html.FROM_HTML_MODE_COMPACT;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dengemo.TekWulf.CANBusGuardian.room.UserRepository;
import com.dengemo.TekWulf.CANBusGuardian.room.entity.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity {
    private FloatingActionButton buttonReturn;
    private EditText textUsername;
    private EditText textPassword;
    private EditText textPasswordConfirm;
    private Drawable eye_close;
    private Drawable eye_open;
    private Drawable lockLogo;
    private Drawable confirmRight;
    private Drawable confirmWrong;
    private MaterialButton buttonSignUp;
    private CheckBox checkBoxProtocol;
    private CheckBox checkBoxAutoSignIn;
    private Boolean eye_state = true;

    UserRepository mRepository;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setFullScreen();
        findViewById(R.id.relativeLayout).setPadding(0, getStatusBarHeight(), 0, 0);

        mRepository = new UserRepository(getApplication());

        initPara();
        initEvent();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initPara() {

        buttonReturn = findViewById(R.id.floatingActionButtonBackToLoginPage);
        textUsername = findViewById(R.id.editTextUsername);
        textPassword = findViewById(R.id.editTextPassword);
        //?????????????????????????????????
        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ")) {
                    String[] tempInputStrUnchecked = s.toString().split(" ");
                    StringBuilder tempInputStrChecked = new StringBuilder();
                    for (String value : tempInputStrUnchecked) {
                        tempInputStrChecked.append(value);
                    }
                    textPassword.setText(tempInputStrChecked.toString());
                    textPassword.setSelection(start);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        textPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        //?????????????????????????????????
        textPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ")) {
                    String[] tempInputStrUnchecked = s.toString().split(" ");
                    StringBuilder tempInputStrChecked = new StringBuilder();
                    for (String value : tempInputStrUnchecked) {
                        tempInputStrChecked.append(value);
                    }
                    textPasswordConfirm.setText(tempInputStrChecked.toString());
                    textPasswordConfirm.setSelection(start);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        buttonSignUp = findViewById(R.id.buttonLogin);
        checkBoxProtocol = findViewById(R.id.checkBoxUserProtocol);
        TextView textUserServiceAgreement = findViewById(R.id.textViewUserServiceAgreement);
        TextView textPrivacyPolicy = findViewById(R.id.textViewPrivacyPolicy);
        checkBoxAutoSignIn = findViewById(R.id.checkBoxAutoSignIn);
        eye_open = getResources().getDrawable(R.drawable.eyes_open, null);
        eye_open.setBounds(0, 0, 55, 55);
        eye_close = getResources().getDrawable(R.drawable.eyes_close, null);
        eye_close.setBounds(0, 0, 55, 55);
        lockLogo = getResources().getDrawable(R.drawable.password, null);
        lockLogo.setBounds(0, 0, 55, 55);
        confirmRight = getResources().getDrawable(R.drawable.right, null);
        confirmRight.setBounds(0, 0, 48, 48);
        confirmWrong = getResources().getDrawable(R.drawable.wrong, null);
        confirmWrong.setBounds(0, 0, 48, 48);
        textUserServiceAgreement.setText(Html.fromHtml("<a href='https://www.dengemo.com'>??????????????????</a>", FROM_HTML_MODE_COMPACT));
        textUserServiceAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        textPrivacyPolicy.setText(Html.fromHtml("<a href='https://www.dengemo.com'>????????????</a>", FROM_HTML_MODE_COMPACT));
        textPrivacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent(){
        buttonReturn.setOnClickListener(returnBtnOnClickListener);
        textPassword.setOnTouchListener(passwordOnTouchListener);
        textPasswordConfirm.setOnFocusChangeListener(passwordConfirmOnFocusChangeListener);
        buttonSignUp.setOnClickListener(signInOnClickListener);
    }

    private void showAlertDialog(String message){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(SignInActivity.this);
        normalDialog.setMessage(message);
        normalDialog.setNegativeButton("??????", null);
        normalDialog.show();
    }

    //?????????????????????
    public int getStatusBarHeight() {
        int result = 0;
        //??????????????????????????????id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //??????????????????
    private void setFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );
    }

    //????????????????????????????????????
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // ????????????????????????????????????????????????TouchEvent???
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View view, MotionEvent event) {
        if (view instanceof EditText) {
            int[] leftTop = {0, 0};
            //????????????????????????location??????
            view.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // ??????????????????????????????????????????EditText?????????
                return false;
            } else {
                //???EditText??????????????????????????????
                view.setFocusable(false);
                view.setFocusableInTouchMode(true);
                return true;
            }
        }
        return false;
    }

    //??????????????????????????????
    private boolean checkPassWord(String password) {
        boolean hasSpace = false;
        boolean hasDigit = false;
        boolean hasLetter = false;
        boolean lengthIsCorrect = false;

        //??????????????????????????????????????????????????????
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if(Character.isSpaceChar(c)){
                hasSpace = true;
                break;
            }
            if(Character.isDigit(c)){
                hasDigit = true;
            }
            if(Character.isUpperCase(c)||Character.isLowerCase(c)){
                hasLetter = true;
            }
        }

        //??????????????????
        if(password.length() > 7 && password.length() < 17){
            lengthIsCorrect = true;
        }

        return (!hasSpace && hasDigit && hasLetter && lengthIsCorrect);
    }

    //?????????????????????????????????
    private boolean checkUsername(String username) {
        boolean isChecked = true;
        //???????????????????????????
        //???????????????????????????????????????
        return isChecked;
    }

    //???????????????????????????????????????????????????
    private boolean checkInput() {
        boolean isChecked = true;
        if (textUsername.getText().toString().equals("") && (textPassword.getText().toString().equals("") || textPasswordConfirm.getText().toString().equals(""))) {
            showAlertDialog("?????????????????????");
            isChecked = false;
        }else if (!checkUsername(textUsername.getText().toString())){
            showAlertDialog("??????????????????");
            isChecked = false;
        }else if (!checkPassWord(textPassword.getText().toString())){
            showAlertDialog("?????????????????????8-16??????????????????????????????????????????????????????");
            isChecked = false;
        }else if (!textPassword.getText().toString().equals(textPasswordConfirm.getText().toString())){
            showAlertDialog("???????????????????????????");
            isChecked = false;
        }else if (!checkBoxProtocol.isChecked()){
            showAlertDialog("?????????????????????????????????????????????");
            isChecked = false;
        }
        return isChecked;
    }

    //??????????????????????????????????????????
    private void loadInDatabase() {
        User user = new User();
        user.username = textUsername.getText().toString();
        user.password = textPassword.getText().toString();
        mRepository.insert(user);
    }

    //?????????????????????
    private void signUp() {
        //????????????
        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();

        //????????????????????????
        loadInDatabase();

        //????????????
        if(checkBoxAutoSignIn.isChecked()){
            //??????intent,???????????????LoginSuccessActivity
            Intent intent = new Intent(SignInActivity.this, MainPageActivity.class);
            //??????activity
            startActivity(intent);
        }else {
            finish();
        }

    }

    View.OnClickListener returnBtnOnClickListener = view -> finish();

    View.OnTouchListener passwordOnTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction()!=MotionEvent.ACTION_UP){
                return false;
            }
            textPassword.setCompoundDrawables(lockLogo, null, eye_close,null);
            Drawable currentDrawable = textPassword.getCompoundDrawables()[2];

            if(motionEvent.getX()>textPassword.getWidth()-textPassword.getPaddingRight()-currentDrawable.getIntrinsicWidth()){
                if(eye_state){
                    textPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    textPassword.setCompoundDrawables(lockLogo, null, eye_open, null);
                }else {
                    textPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    textPassword.setCompoundDrawables(lockLogo, null, eye_close, null);
                }
                eye_state = !eye_state;
            }
            return false;
        }
    };

    View.OnClickListener signInOnClickListener = view -> {
        if(checkInput()) {
            signUp();
        }
    };

    View.OnFocusChangeListener passwordConfirmOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(!b){
                if(textPassword.getText().toString().equals(textPasswordConfirm.getText().toString())){
                    if(textPassword.getText().toString().equals("")){
                        textPasswordConfirm.setCompoundDrawables(lockLogo, null, confirmWrong,null);
                    }else {
                        textPasswordConfirm.setCompoundDrawables(lockLogo, null, confirmRight,null);
                    }
                }else {
                    textPasswordConfirm.setCompoundDrawables(lockLogo, null, confirmWrong,null);
                }
            }
        }
    };
}
