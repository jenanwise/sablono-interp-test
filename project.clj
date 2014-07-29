(defproject sablono-interp-test "0.1.0-SNAPSHOT"
  :description "Minimal example for sablono interpreter dynamic children bug."

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2277"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [sablono "0.2.18"]
                 [om "0.6.5"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :source-paths ["src"]

  :cljsbuild {:builds [{:id "sablono-interp-test"
                        :source-paths ["src"]
                        :compiler {:output-to "sablono_interp_test.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true}}]})
