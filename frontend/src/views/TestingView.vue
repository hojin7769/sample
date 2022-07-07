<template>
  <div id='myMain'>       
                    <h2>  {{ title }}  </h2>
                    {{ title2 }}                
                    <br><br>
                    
                    <table>
                        <draggable :list="schoolInfo" >
                            <template v-for='(school, index) in schoolInfo' >
                                <tr>
                                    <td>
                                        <input v-model="school['name']"> 
                                    </td>
                                    <td>
                                        <input v-model="school['address']" > 
                                    </td>
                                    <td>
                                        <input v-model="school['city']"> 
                                    </td>
                                    <td>
                                        <input v-model="school['phone']" > 
                                    </td>
                                </tr>
                            </template>
                        </draggable>
                    </table>        
            </div>

    <rawDisplayer class="col-3" :value="list" title="List" />
  </div>
</template>
<script>
                const schoolData = Vue.createApp({
                    
                    data() {
                        return {
                            title:"this is my school",
                            title2:'title2 here it iss',
                            
                                schoolInfo: [
                                    { id: 0,
                                        name:"school1",
                                        address:"address1",
                                        city:"city1",
                                        phone:"phone1"
                                    },
                                    { id: 1,
                                        name:"school2",
                                        address:"address2",
                                        city:"city2",
                                        phone:"phone2"
                                    },
                                    { id: 3,
                                        name:"school3",
                                        address:"address3",
                                        city:"city3",
                                        phone:"phone3"
                                    },
                                    { id: 4,
                                        name:"school4",
                                        address:"address4",
                                        city:"city4",
                                        phone:"phone4"
                                    }
                                        
                                    ],
                                }
                            }
                        })
            schoolData.component('draggable',VueDraggableNext)       
            schoolData.mount('#myMain')
            </script>
<style scoped>
.buttons {
  margin-top: 35px;
}
</style>