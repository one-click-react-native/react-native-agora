package io.agora.rtc.base

import android.content.Context
import android.view.TextureView
import android.widget.FrameLayout
import io.agora.rtc.RtcChannel
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import java.lang.ref.WeakReference
import io.agora.rtc.mediaio.MediaIO.BufferType.TEXTURE
import io.agora.rtc.mediaio.MediaIO.PixelFormat.TEXTURE_OES
import io.agora.rtc.mediaio.AgoraTextureView
import io.agora.rtc.mediaio.AgoraTextureCamera
import io.agora.rtc.base.CommanInstance

class RtcTextureView(
  context: Context
) : FrameLayout(context) {
  private var renderer: AgoraTextureView? = null
  private var texture: TextureView? = null
  // private var canvas: VideoCanvas
  private var channel: WeakReference<RtcChannel>? = null

  init {
    try {
      val engine: RtcEngine? = CommanInstance.Companion.getRtcEngine();
      var _source: AgoraTextureCamera? = CommanInstance.Companion.getAgoraTextureCamera()
      renderer = AgoraTextureView(context)
      renderer?.init(_source?.getEglContext())
      renderer?.setMirror(true)
      addView(renderer)
      engine?.setLocalVideoRenderer(renderer)
      println("RtcTextureView init local video setup complete")
    } catch (e: UnsatisfiedLinkError) {
      throw RuntimeException("Please init RtcEngine first!")
    }
  }

  fun setData(engine: RtcEngine, channel: RtcChannel?, uid: Int) {
    println("RtcTextureView setData called but no implementation")
    // this.channel = if (channel != null) WeakReference(channel) else null
    // canvas.channelId = this.channel?.get()?.channelId()
    // canvas.uid = uid
    //setupVideoCanvas(engine)
  }

  fun resetVideoCanvas(engine: RtcEngine) {
    println("RtcTextureView resetVideoCanvas called but no implementation")
    // val canvas = VideoCanvas(null, canvas.renderMode, canvas.channelId, canvas.uid, canvas.mirrorMode)
    // if (canvas.uid == 0) {
      //engine.setupLocalVideo(canvas)
    // } else {
      // println("Texture View can only be used for local video because of custom video source implementation.")
    // }
  }

  private fun setupVideoCanvas(engine: RtcEngine) {
    println("RtcTextureView setupVideoCanvas called")
    removeAllViews()
      var _source: AgoraTextureCamera? = CommanInstance.Companion.getAgoraTextureCamera()
      renderer = AgoraTextureView(context.applicationContext)
      renderer?.init(_source?.getEglContext())
      renderer?.setMirror(true)
      addView(renderer)
      renderer?.layout(0, 0, width, height)
      engine.setLocalVideoRenderer(renderer)
      println("RtcTextureView setupVideoCanvas local video setup complete")
  }

  fun setRenderMode(engine: RtcEngine, @Annotations.AgoraVideoRenderMode renderMode: Int) {
    println("RtcTextureView setRenderMode called but no implementation")
    //canvas.renderMode = renderMode
    //setupRenderMode(engine)
  }

  fun setMirrorMode(engine: RtcEngine, @Annotations.AgoraVideoMirrorMode mirrorMode: Int) {
    println("RtcTextureView setMirrorMode called but no implementation")
    //canvas.mirrorMode = mirrorMode
    //setupRenderMode(engine)
  }

  private fun setupRenderMode(engine: RtcEngine) {
    println("RtcTextureView setupRenderMode called but no implementation")
    // if (canvas.uid == 0) {
    //   println("RtcTextureView setupRenderMode called with canvas uid = 0")
    //   //engine.setLocalRenderMode(canvas.renderMode, canvas.mirrorMode)
    // } else {
    //   println("Texture View can only be used for local video because of custom video source implementation.")
    // }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    println("RtcTextureView onMeasure called")
    val width: Int = MeasureSpec.getSize(widthMeasureSpec)
    val height: Int = MeasureSpec.getSize(heightMeasureSpec)
    renderer?.layout(0, 0, width, height)
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }
}
