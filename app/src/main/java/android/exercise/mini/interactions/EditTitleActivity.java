package android.exercise.mini.interactions;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  // TODO:
  //  you can add fields to this class. those fields will be accessibly inside any method
  //  (like `onCreate()` and `onBackPressed()` methods)
  // for any field, make sure to set it's initial value. You CAN'T write a custom constructor
  // for example, you can add this field:
  // `private boolean isEditing = false;`
  // in onCreate() set `this.isEditing` to `true` once the user starts editing, set to `false` once done editing
  // in onBackPressed() check `if(this.isEditing)` to understand what to do
  private boolean _isEditing = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "start edit" FAB
      2. animate in the "done edit" FAB
      3. hide the static title (text-view)
      4. show the editable title (edit-text)
      5. make sure the editable title's text is the same as the static one
      6. optional (HARD!) make the keyboard to open with the edit-text focused,
          so the user can start typing without the need another click on the edit-text

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
      _isEditing = true;
      // 1. animate out the "start edit" FAB
      fabStartEdit.animate().alpha(0f).setDuration(300L).start();
      fabStartEdit.setVisibility(View.GONE);
      // 2. animate in the "done edit" FAB
      fabEditDone.setAlpha(0f);
      fabEditDone.setVisibility(View.VISIBLE);
      fabEditDone.animate().alpha(1f).setDuration(300L).start();
      // 3. hide the static title (text-view)
      textViewTitle.setVisibility(View.GONE);
      // 4. show the editable title (edit-text)
      editTextTitle.setVisibility(View.VISIBLE);
      // 5. make sure the editable title's text is the same as the static one
      editTextTitle.setText(textViewTitle.getText());
      //       6. optional (HARD!) make the keyboard to open with the edit-text focused,
      //          so the user can start typing without the need another click on the edit-text
      editTextTitle.requestFocus();
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(editTextTitle, InputMethodManager.SHOW_IMPLICIT);
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "done edit" FAB
      2. animate in the "start edit" FAB
      3. take the text from the user's input in the edit-text and put it inside the static text-view
      4. show the static title (text-view)
      5. hide the editable title (edit-text)
      6. make sure that the keyboard is closed

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
      //       1. animate out the "done edit" FAB
      fabEditDone.animate().alpha(0f).setDuration(300L).start();
      fabEditDone.setVisibility(View.GONE);
      //      2. animate in the "start edit" FAB
      fabStartEdit.setAlpha(0f);
      fabStartEdit.setVisibility(View.VISIBLE);
      fabStartEdit.animate().alpha(1f).setDuration(300L).start();
      //       3. take the text from the user's input in the edit-text and put it inside the static text-view
      String userInputTitle = editTextTitle.getText().toString();
      textViewTitle.setText(userInputTitle);
      //       4. show the static title (text-view)
      textViewTitle.setVisibility(View.VISIBLE);
      //       5. hide the editable title (edit-text)
      editTextTitle.setVisibility(View.GONE);
      //       6. make sure that the keyboard is closed
      closeKeyBoard(textViewTitle);
      _isEditing = false;
    });
  }

  @Override
  public void onBackPressed() {
    // BACK button was clicked
    /*
    TODO:
    if user is now editing, tap on BACK will revert the edit. do the following:
    1. hide the edit-text
    2. show the static text-view with previous text (discard user's input)
    3. animate out the "done-edit" FAB
    4. animate in the "start-edit" FAB

    else, the user isn't editing. continue normal BACK tap behavior to exit the screen.
    call `super.onBackPressed()`

    notice:
    to work with views, you will need to find them first.
    to find views call `findViewById()` in a same way like in `onCreate()`
     */
    if (_isEditing){
      // find all views // TODO Change it to data members?
      FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
      FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
      TextView textViewTitle = findViewById(R.id.textViewPageTitle);
      EditText editTextTitle = findViewById(R.id.editTextPageTitle);

      editTextTitle.setVisibility(View.GONE);

      textViewTitle.setVisibility(View.VISIBLE);

      fabEditDone.animate().alpha(0f).setDuration(300L).start();
      fabEditDone.setVisibility(View.GONE);

      fabStartEdit.animate().alpha(1f).setDuration(300L).start();
      fabStartEdit.setVisibility(View.VISIBLE);

      closeKeyBoard(textViewTitle);
      _isEditing = false;
    }
    else{
      super.onBackPressed();
    }
  }

  private void closeKeyBoard(View view){
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }
}