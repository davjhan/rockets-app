package com.rockets.uiscreens.modals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.rockets.assets.Font;
import com.rockets.assets.Icons;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;

import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookCallback;
import de.tomgrill.gdxfacebook.core.GDXFacebookError;
import de.tomgrill.gdxfacebook.core.GDXFacebookSystem;
import de.tomgrill.gdxfacebook.core.SignInMode;
import de.tomgrill.gdxfacebook.core.SignInResult;

/**
 * name: FacebookConversionModal
 * desc:
 * date: 2017-06-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class FacebookConversionModal extends BasicModal {
    HanButton connectButton;
    HanButton cancelButton;
    HanLabel descLabel;
    public FacebookConversionModal(IApp app, ModalListener modalListener) {
        super(app, modalListener, true, true);
        init();
    }

    @Override
    protected void initContents() {
        descLabel = HanLabel.text(app.getString("facebook_conversion_copy"))
                .align(Align.left)
                .wrap(true)
                .build();

        connectButton = HanButton.with(app)
                .text(app.getString("continue"))
                .style(HanButton.PRIMARY)
                .fontName(Font.h2)
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        logInToFacebook();
                    }


                })
                .build();

        cancelButton = HanButton.with(app)
                .text(app.getString("maybe_later"))
                .leftIcon(app.menuAssets().icons[Icons.BACK])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        closeModal();
                    }
                })
                .build();


        contents.add(descLabel).width(180).grow();
        contents.row();
        contents.add(connectButton).spaceTop(Spacing.REG).grow();
        contents.row();
        contents.add(cancelButton).spaceTop(Spacing.SMALL).grow();
    }
    private void logInToFacebook() {
        GDXFacebook gdxFacebook = GDXFacebookSystem.getFacebook();
        Array<String> permissions = new Array<>();
        permissions.add("public_profile");
        gdxFacebook.signIn(SignInMode.READ, permissions, new GDXFacebookCallback<SignInResult>() {
            @Override
            public void onSuccess(SignInResult result) {
                // Login successful
                Gdx.app.log("tttt FacebookConversionModal", "onSuccess: " +result);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        InfoModal infoModal = new InfoModal(app,app.getString("success"),"Successfully logged into Facebook.");
                        app.showModal(infoModal);
                        closeModal();
                    }
                });
                Gdx.app.log("tttt FacebookConversionModal", "access token: " +GDXFacebookSystem.getFacebook().getAccessToken().toString());

            }

            @Override
            public void onError(GDXFacebookError error) {
                // Error handling
                Gdx.app.log("tttt FacebookConversionModal", "error: " +error);
                InfoModal infoModal = new InfoModal(app,app.getString("error"),"Error Logging into Facebook. "
                +error.getErrorMessage());
                app.showModal(infoModal);
            }

            @Override
            public void onCancel() {
                // When the user cancels the login process
                Gdx.app.log("tttt FacebookConversionModal", "Canceled: ");
            }

            @Override
            public void onFail(Throwable t) {
                // When the login fails
                Gdx.app.log("tttt FacebookConversionModal", "error: " +t);

            }
        });
    }
    @Override
    public String getTitleString() {
        return app.getString("connect_to_facebook");
    }
}
