<template>
  <div>
        Count machines: {{ allMachines }} <br>
       {{ machines }}
  </div>
</template>

<script>
import Constants from './Constants.js'

export default {
    name: "Machines",
    data() {
        return {
            machines: []
        }
    },
    methods: {
        getMachines() {
            console.log(Constants.machinesURI)
            const baseURI = Constants.machinesURI
            this.$http.get(baseURI)
                .then(result => this.machines = result.data)
        }
    }, 
    mounted() {
        this.getMachines()
    }
}
</script>

<style>

</style>