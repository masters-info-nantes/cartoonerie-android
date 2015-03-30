package com.cartoonerie.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.net.URI;


public class ProjectActivity extends Activity {

    private Project project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra(ProjectsActivity.PROJECT);
        getActionBar().setTitle(project.getName());

        VideoView videoView = (VideoView)findViewById(R.id.video_view);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setKeepScreenOn(true);
        videoView.setVideoPath(project.getVideoPath());
        videoView.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_delete:
                ProjectDB db = new ProjectDB(this);
                db.removeProject(project);
                Intent intent = new Intent(getApplicationContext(), ProjectsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
