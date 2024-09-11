package ru.vtvhw

import org.springframework.core.io.ClassPathResource
import ru.vtvhw.scopes.ApiKey
import ru.vtvhw.scopes.GooglePlayMarket
import ru.vtvhw.scopes.MobileApp
import ru.vtvhw.scopes.RuStoreMarket

def properties = new Properties()
properties.load(new ClassPathResource('application.properties').inputStream)

beans {
    apiKey(ApiKey) {
        value = properties.getProperty('apiKey.value', 'API Key is not set!')
    }

    if (properties.getProperty('market.name', 'ruStore') == 'ruStore') {
        market(RuStoreMarket, apiKey) {
//            apiKey = ref('apiKey')
            bean -> {
                bean.scope = "singleton"
                bean.lazyInit = 'true'
                bean.destroyMethod = "doDestroy"
            }
        }
    } else {
        market(GooglePlayMarket, apiKey) {
            bean -> {
                bean.scope = "singleton"
                bean.lazyInit = 'true'
                bean.destroyMethod = "doDestroy"
            }
        }
    }

    mobileApp(MobileApp, properties.getProperty('mobileApp.defaultName', 'Prototype scope')) {
        bean ->
            bean.scope = "prototype"
            bean.initMethod = "doSomeInit"
    }
}