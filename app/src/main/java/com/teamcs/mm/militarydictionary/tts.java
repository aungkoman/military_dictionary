package com.teamcs.mm.militarydictionary;

import android.speech.tts.*;
import android.widget.*;
import android.content.*;
import java.util.*;

public class tts
{
    TextToSpeech ttobj;
    final Context context;
    tts(Context c){
        this.context=c;
        ttobj=new  TextToSpeech(context,
                new  TextToSpeech.OnInitListener()  {
                    @Override
                    public  void  onInit(int  status)  {
                        if(status  !=  TextToSpeech.ERROR){ttobj.setLanguage(Locale.UK);}
                        else{Toast.makeText(context,  "Can't initialize Text to Speech feature",   Toast.LENGTH_SHORT).show();
                        }
                    }
                    // onInit end
                }); // new tts listner end

    }

    public  void  speakText(String toSpeak){
        //Toast.makeText(context,toSpeak,   Toast.LENGTH_SHORT).show();

        ttobj.speak(toSpeak,  TextToSpeech.QUEUE_FLUSH,  null);
    }
}
