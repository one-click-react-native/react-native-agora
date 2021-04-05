package io.agora.rtc.base
import android.content.Context
import io.agora.rtc.mediaio.AgoraTextureCamera;
import io.agora.rtc.RtcEngine

class CommanInstance { 
  companion object {
    val context: Context = null
    val rtcEngine: RtcEngine = null
    val agoraTextureCamera: AgoraTextureCamera = null
    fun getContext(): Context { return context }
    fun setContext(ctx: Context): void { Context = ctx }
    fun getRtcEngine(): RtcEngine { return rtcEngine }
    fun setRtcEngine(engine: RtcEngine): void { rtcEngine = engine }
    fun getAgoraTextureCamera(): AgoraTextureCamera { return agoraTextureCamera }
    fun setAgoraTextureCamera(textureCamera: AgoraTextureCamera): void { agoraTextureCamera = textureCamera }
    fun clean(): void {
        context = null
        rtcEngine = null
        agoraTextureCamera = null
    }
  } 
}