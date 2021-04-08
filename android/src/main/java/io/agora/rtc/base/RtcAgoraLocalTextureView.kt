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

class RtcAgoraLocalTextureView(
  context: Context
) : FrameLayout(context) {
  private var renderer: AgoraTextureView? = null
  private var texture: TextureView? = null
  private var channel: WeakReference<RtcChannel>? = null

  init {
    try {
      val engine: RtcEngine? = CommanInstance.Companion.getRtcEngine();
      var _source: AgoraTextureCamera? = CommanInstance.Companion.getAgoraTextureCamera()
      renderer = AgoraTextureView(context)
      renderer?.init(_source?.getEglContext())
      renderer?.setBufferType(TEXTURE);
      renderer?.setPixelFormat(TEXTURE_OES)
      addView(renderer)
      engine?.setLocalVideoRenderer(renderer)
    } catch (e: UnsatisfiedLinkError) {
      throw RuntimeException("Please init RtcEngine first!")
    }
  }

  fun setData(engine: RtcEngine, channel: RtcChannel?, uid: Int) {
  }

  fun resetVideoCanvas(engine: RtcEngine) {
  }

  private fun setupVideoCanvas(engine: RtcEngine) {
    removeAllViews()
      var _source: AgoraTextureCamera? = CommanInstance.Companion.getAgoraTextureCamera()
      renderer = AgoraTextureView(context.applicationContext)
      renderer?.init(_source?.getEglContext())
      renderer?.setMirror(true)
      addView(renderer)
      renderer?.layout(0, 0, width, height)
      engine.setLocalVideoRenderer(renderer)
  }

  fun setRenderMode(engine: RtcEngine, @Annotations.AgoraVideoRenderMode renderMode: Int) {
  }

  fun setMirrorMode(engine: RtcEngine, @Annotations.AgoraVideoMirrorMode mirrorMode: Int) {
  }

  private fun setupRenderMode(engine: RtcEngine) {
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val width: Int = MeasureSpec.getSize(widthMeasureSpec)
    val height: Int = MeasureSpec.getSize(heightMeasureSpec)
    renderer?.layout(0, 0, width, height)
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }
}
