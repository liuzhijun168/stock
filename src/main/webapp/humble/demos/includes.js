window.FlashCanvasOptions = {
  swfPath: '/stock/humble/lib/FlashCanvas/bin/'
};
yepnope([
  // Libs
  '/stock/humble/lib/jquery/jquery-1.7.1.min.js',
  '/stock/humble/lib/flotr2/lib/bean.js',
  '/stock/humble/lib/flotr2/lib/underscore-min.js',
  {
  test : (navigator.appVersion.indexOf("MSIE") != -1  && parseFloat(navigator.appVersion.split("MSIE")[1]) < 9),
    // Load for IE < 9
    yep : [
      '/stock/humble/lib/FlashCanvas/bin/flashcanvas.js',
      /*'/stock/humble/lib/flotr2/lib/excanvas.js',*/
      '/stock/humble/lib/flotr2/lib/base64.js'
    ]
  },
  '/stock/humble/lib/flotr2/lib/canvas2image.js',
  /*'/stock/humble/lib/flotr2/lib/canvastext.js',*/
  '/stock/humble/lib/bonzo/bonzo.min.js',

  // Flotr
  '/stock/humble/lib/flotr2/js/Flotr.js',
  '/stock/humble/lib/flotr2/js/DefaultOptions.js',
  '/stock/humble/lib/flotr2/js/Color.js',
  '/stock/humble/lib/flotr2/js/Date.js',
  '/stock/humble/lib/flotr2/js/DOM.js',
  '/stock/humble/lib/flotr2/js/EventAdapter.js',
  '/stock/humble/lib/flotr2/js/Graph.js',
  '/stock/humble/lib/flotr2/js/Axis.js',
  '/stock/humble/lib/flotr2/js/Series.js',
  '/stock/humble/lib/flotr2/js/Text.js',
  '/stock/humble/lib/flotr2/js/types/lines.js',
  '/stock/humble/lib/flotr2/js/types/bars.js',
  '/stock/humble/lib/flotr2/js/types/points.js',
  '/stock/humble/lib/flotr2/js/plugins/selection.js',
  '/stock/humble/lib/flotr2/js/plugins/legend.js',
  '/stock/humble/lib/flotr2/js/plugins/hit.js',
  '/stock/humble/lib/flotr2/js/plugins/crosshair.js',
  '/stock/humble/lib/flotr2/js/plugins/labels.js',
  '/stock/humble/lib/flotr2/js/plugins/legend.js',
  '/stock/humble/lib/flotr2/js/plugins/titles.js',
  {
    test : ('ontouchstart' in window),
    nope : [
      '/stock/humble/lib/flotr2/js/plugins/handles.js'
    ]
  },

  // Visualization
  '/stock/humble/js/Envision.js',
  '/stock/humble/js/Visualization.js',
  '/stock/humble/js/Component.js',
  '/stock/humble/js/Interaction.js',
  '/stock/humble/js/Preprocessor.js',
  '/stock/humble/js/templates/namespace.js',
  '/stock/humble/js/templates/Finance.js',
  '/stock/humble/js/templates/TimeSeries.js',
  '/stock/humble/js/templates/Zoom.js',
  '/stock/humble/js/actions/namespace.js',
  '/stock/humble/js/actions/hit.js',
  '/stock/humble/js/actions/selection.js',
  '/stock/humble/js/actions/zoom.js',
  '/stock/humble/js/adapters/namespace.js',
  '/stock/humble/js/adapters/flotr/namespace.js',
  '/stock/humble/js/adapters/flotr/defaultOptions.js',
  '/stock/humble/js/adapters/flotr/Child.js',
  '/stock/humble/js/adapters/flotr/lite-lines.js',
  '/stock/humble/js/adapters/flotr/whiskers.js',
  '/stock/humble/js/components/namespace.js',
  '/stock/humble/js/components/QuadraticDrawing.js',

  { complete : example }
]);
