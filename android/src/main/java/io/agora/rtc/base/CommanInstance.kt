package io.agora.rtc.base
import android.content.Context
import io.agora.rtc.mediaio.AgoraTextureCamera;
import io.agora.rtc.RtcEngine

public class CommanInstance { 
  companion object {
    private var rtcEngine: RtcEngine? = null
    private var agoraTextureCamera: AgoraTextureCamera? = null
    fun getRtcEngine(): RtcEngine? {
      println("CommanInstance getRtcEngine $rtcEngine")
      return rtcEngine
    }
    fun setRtcEngine(engine: RtcEngine?) {
      rtcEngine = engine
      println("CommanInstance setRtcEngine")
    }
    fun getAgoraTextureCamera(): AgoraTextureCamera? {
      println("CommanInstance getAgoraTextureCamera")
      return agoraTextureCamera
    }
    fun setAgoraTextureCamera(textureCamera: AgoraTextureCamera?) {
      agoraTextureCamera = textureCamera
      println("CommanInstance setAgoraTextureCamera")
    }
    fun clean() {
        rtcEngine = null
        agoraTextureCamera = null
        println("CommanInstance clean")
    }
  } 
}