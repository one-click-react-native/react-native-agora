package io.agora.rtc.react

import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import io.agora.rtc.RtcChannel
import io.agora.rtc.RtcEngine
import io.agora.rtc.base.RtcAgoraLocalTextureView

class RCTAgoraRtcLocalTextureViewManager : SimpleViewManager<RtcAgoraLocalTextureView>() {
  companion object {
    const val REACT_CLASS = "RCTAgoraRtcLocalTextureView"
  }

  private var reactContext: ThemedReactContext? = null

  override fun createViewInstance(reactContext: ThemedReactContext): RtcAgoraLocalTextureView {
    this.reactContext = reactContext
    return RtcAgoraLocalTextureView(reactContext)
  }

  override fun onDropViewInstance(view: RtcAgoraLocalTextureView) {
    // getEngine()?.let { view.resetVideoRender(it) }
    super.onDropViewInstance(view)
  }

  override fun getName(): String {
    return REACT_CLASS
  }

  @ReactProp(name = "data")
  fun setData(view: RtcAgoraLocalTextureView, data: ReadableMap) {
    data.toHashMap().let { map ->
      val channel = (map["channelId"] as? String)?.let { getChannel(it) }
      getEngine()?.let { view.setData(it, channel, (map["uid"] as Number).toInt()) }
    }
  }

  @ReactProp(name = "renderMode")
  fun setRenderMode(view: RtcAgoraLocalTextureView, renderMode: Int) {
    getEngine()?.let { view.setRenderMode(it, renderMode) }
  }

  @ReactProp(name = "mirrorMode")
  fun setMirrorMode(view: RtcAgoraLocalTextureView, mirrorMode: Int) {
    getEngine()?.let { view.setMirrorMode(it, mirrorMode) }
  }

  private fun getEngine(): RtcEngine? {
    return reactContext?.getNativeModule(RCTAgoraRtcEngineModule::class.java)?.engine()
  }

  private fun getChannel(channelId: String): RtcChannel? {
    return reactContext?.getNativeModule(RCTAgoraRtcChannelModule::class.java)?.channel(channelId)
  }
}
