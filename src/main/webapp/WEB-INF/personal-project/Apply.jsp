<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>



        <div id="tabs">
            <!--  labels for tabs  -->
            <ul>
                <li><a href="#personal">Personal Information</a></li>
                <li><a href="#history">Skating History</a></li>
                <li><a href="#gear">Gear</a></li>
                <li><a href="#practice">Practice Sign Up </a></li>
                <li><a href="#submit">Submit Application</a></li>
            </ul>
            <div id="personal">

                <p>Personal Information</p>
                <div class = "container">

                    <div class = "container">
                        <!--form zero!-->
                        <form id="testForm0">

                            <div class = "row" id = "row1">
                                <div class = "col-xl-3" id="r1c1">

                                    <input id="FirstName" name = "FirstName" value="" type="text" class="errorInput" />

                                    <label for="FirstName">First Name</label>
                                </div>
                                <div class="col-xl-3" id="r1c2">
                                    <input id="LastName" name = "LastName" type="text" class="errorInput" />

                                    <label for="LastName">Last Name</label>
                                </div>
                                <div class="col-xl-3" id="r1c3">

                                    <input id="email" name = "email" type="text" class="errorInput" />
                                    <label for="email">Email</label>


                                </div>
                            </div> <!-- end row 1-->
                            <div class = "row" id = "row2"> <!-- start row 2-->
                                <div class="col-xl-3" id="r2c1">
                                    <select id="Pronouns" input type="text" class="errorInput" ></select>

                                    <p>Pronouns</p>

                                </div>

                                <div class="col-xl-7" id="r2c2">
                                    <p> <br/>Do you have a derby name?
                                        <input type="radio" id="derbynameyes" name="derbyName" value="yes" />
                                        yes
                                        <input type="radio" id="derbynameno" name="derbyName" value ="no" checked />
                                        no</p><br/>
                                </div>





                            </div> <!-- end row 2-->
                            <div class = "row" id = "row3"> <!-- start row 3 -->

                                <div class = "col-sm-3" id = "r3c1">
                                    <input type="checkbox" id="18+" /> <p>18+?</p>
                                </div>
                                <div class="col-lg-4" id="r2c3">



                                    <input type="text" class="derby_name" id="derbynameinput"></input>
                                    <p class="derby_name">Derby Name</p>

                                </div>
                                <div class="col-sm-10" id="r3c2"><p>You must be 18 Years to Join the team.</p></div>


                                <button type="button" type="submit" class="text-left" value="Submit" id="tab0submit" ></button>
                            </div> <!-- end row 4-->

                        </form>
                    </div>

                </div> <!-- end inner container-->

            </div> <!-- end outer container-->

            <div id="history">


                <form id="testForm1">

                    <div class="row">
                        <div class="col-sm-4"><p>Do you have WFTDA insurance?</p></div>
                        <div class ="col-sm-1" ><input type="radio" id="InsuranceYes" name="Insurance" value="yes" />yes</div>
                        <div class ="col-sm-1" ><input type="radio" id="InsuranceNo" name="Insurance" value="no" checked/>no</div>
                    </div>
                    <div class="row" id="WFTDArow">
                        <div class="col-sm-5"><p>WFTDA insurance number</p></div>
                        <div class="col-sm-6"><input id="WFTDANumber" input type="text" class="errorInput3" /> 	</div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4"><p>Do you have any previous Experiance?</p></div>
                        <div class ="col-sm-1" ><input type="radio" id="ExperianceYes" name="Experiance" value="yes" />yes</div>
                        <div class ="col-sm-1" ><input type="radio" id="ExperianceNo" name="Experiance" value="no" checked/>no</div>
                    </div>





                    <!--  team field set to be duplicated -->
                    <fieldset class="teamFieldSet" id="fieldset">
                        <div id="FirstHistorialTeam">
                            <div class ="row" id ="p3r1">

                                <div class ="col-sm-4" id="p2r1c3">League Affiliation </div>
                                <div class ="col-sm-8" id="p2r1c4"><select id="PreviousLeague1" input type="text" class="errorInput1" ></select> </div>
                            </div>
                            <div class="row" id="p3r2">
                                <div class ="col-sm-4" id="p2r1c1"><p>Team Name</p> </div>
                                <div class ="col-sm-8" id="p2r1c2"><input id="PreviousTeam1" input type="text" class="errorInput1" /> </div>
                            </div>
                            <div> <!-- end row 1-->
                                <div class ="row" id ="p3r3">
                                    <div class ="col-sm-2" id="p2r2c1">City </div>
                                    <div class ="col-sm-3" id="p2r2c2"><input id="city" input type="text" class="errorInput1" /> </div>
                                    <div class ="col-sm-2" id="p2r2c3">State </div>
                                    <div class ="col-sm-3" id="p2r2c4"><select id="state" input type="text" class="errorInput1"  ></select> </div>

                                </div> <!-- end row 2-->




                                <div class ="row" id ="p3r4">
                                    <div class ="col-sm-2" id="p2r3c1">Position </div>
                                    <div class ="col-sm-3" id="p2r3c2"><select id="Position" input type="text" class="errorInput1" ></select> </div>
                                    <div class ="col-sm-2" id="p2r3c3">Jersey Number </div>
                                    <div class ="col-sm-3" id="p2r3c4"><select id="JerseyNumber" input type="text"  class="errorInput1" ></select> </div>
                                </div><!-- end row 3-->
                                <div class ="row" id ="p3r5">
                                    <div class ="col-sm-2" id="p2r4c1"> Start Date</div>
                                    <div class ="col-sm-3"  id="p2r4c2"> <input id="date0" input type="text" class="errorInput1 date" /></div>
                                    <div class ="col-sm-2" id="p2r4c3"> End Date</div>
                                    <div class ="col-sm-3"  id="p2r4c4"> <input id="date1" input type="text" class="errorInput1 date" /></div>
                                </div> <!-- end row 4-->
                                <!-- nd row 4-->

                            </div> <!-- end first historical team-->
                        </div>
                    </fieldset>
                    <button type="button" class="text-left" value="AddTeam" id="AddTeam" >	</button>
                    <button type="button" value="RemoveTeam" id="RemoveTeam" ></button>
                    <button type="button" value="Submit" id="tab1submit" ></button>
                </form>

            </div>
            <div id="gear">


                <form id="testForm2">
                    <Div class="row">
                        <p>The Cedar Rapids Roller Girls have gear if you would like to borrow some.
                            Please select any gear you would like to borrow, and the size you are requesting.
                        </p>
                    </Div>
                    <div class="row" id="p2r1">
                        <div class ="col-sm-3" id="p3r1c1"><p>Do you have a helmet?</p></div>
                        <div class ="col-sm-1" id="p3r1c2"><input type="radio" id="Helmyes" name="Helm" value="yes" /><p>Yes</p></div>
                        <div class ="col-sm-1" id="p3r1c3"><input type="radio" id="Helmno" name="Helm" value="no" checked/><p>No</p></div>
                        <div class ="col-sm-3" id="p3r1c4"><p>Choose a Bucket size</p></div>

                        <div class ="col-sm-4" id="p3r1c5">
                            <select name="HelmDropDown" id="HelmDropDown">

                            </select>
                        </div>
                    </div>


                    <!-- end row 1-->
                    <div class="row" id="p2r2">
                        <div class ="col-sm-3" id="p3r2c1"><p>Do you have wrist guards</p></div>
                        <div class ="col-sm-1" id="p3r2c2"><input type="radio" id="Wristyes" name="Wrist" value="yes" /><p>Yes</p></div>
                        <div class ="col-sm-1" id="p3r2c3"><input type="radio" id="Wristno" name="Wrist" value="no" checked/><p>No</p></div>
                        <div class ="col-sm-3" id="p3r2c4"><p>Choose a Wrist Guard size</p> </div>
                        <div class = "col-sm-4" id="p3r2c5">
                            <select name="WristDropDown" id="WristDropDown">

                            </select>
                        </div>
                    </div> <!-- end row 2-->

                    <div class="row" id="p2r3">
                        <div class ="col-sm-3" id="p3r3c1"><p>Do you have elbow pads</p></div>
                        <div class ="col-sm-1" id="p3r3c2"><input type="radio" id="Elbowyes" name="Elbow" value="yes" /><p>Yes</p></div>
                        <div class ="col-sm-1" id="p3r3c3"><input type="radio" id="Elbowno" name="Elbow" value="no" checked/><p>No</p></div>
                        <div class ="col-sm-3" id="p3r3c4"><p>Choose a Elbow Pad size</p></div>
                        <div class="col-sm-4" id="p3r3c5">
                            <select name="ElbowDropDown" id="ElbowDropDown">

                            </select>

                        </div> <!-- end row 3-->
                    </div>
                    <div class="row" id="p2r4">
                        <div class ="col-sm-3" id="p3r4c1"><p>Do you have Knee Pads?</p></div>
                        <div class ="col-sm-1" id="p3r4c2"><input type="radio" id="Kneeyes" name="Knee" value="yes" /><p>Yes</p></div>
                        <div class ="col-sm-1" id="p3r4c3"><input type="radio" id="Kneeno" name="Knee" value="no" checked/><p>No</p></div>
                        <div class ="col-sm-3" id="p3r4c4"><p>Choose a knee pad size</p></div>
                        <div class="col-sm-4" id = "p3r4c5">
                            <select name="KneeDropDown" id="KneeDropDown">

                            </select>
                        </div>
                    </div> <!-- end row 4-->
                    <div class="row" id="p2r5">
                        <div class ="col-sm-3" id="p3r5c1"><p>Do you have Skates?</p></div>
                        <div class ="col-sm-1" id="p3r5c2"><input type="radio" id="Skatesyes" name="Skates" value="yes" /><p>Yes</p></div>
                        <div class ="col-sm-1" id="p3r5c3"><input type="radio" id="Skatesno" name="Skates" value="no" checked/><p>No</p></div>
                        <div class ="col-sm-3" id="p3r5c4"><p>Choose a shoe size</p></div>
                        <div class="col-sm-4" id="p3r5c5"><select name="SkateDropDown" id="SkateDropDown"></select>
                        </div>




                    </div> <!-- end row 5-->




                    <button type="button" value="Submit" id="tab2submit" ></button>


                </form>
            </div>


            <div id="practice">


                <form id="testForm3">

                    <div class="row" id="p4r1">

                        <div  id="p4r1c2"><p>The Cedar Rapids Roller Girls hold two practices per week. <br/>
                            On Sundays we practice at 7:00 PM at the Vinton Skate & Activity Center in Vinton, Iowa. <br/>
                            On Wednesdays we pratice at 7:30 PM at GameOn Sports CR in Cedar Rapids, Iowa.</br.> </p></div>





                    </div>
                    <div class="row" id="p4r2">
                        <div class="col-sm-3" id="p4r2c1"><p></p></div>
                        <div class="col-sm-6" id="p4r2c2"><input id="PracticeDate" input type="text" class="errorInput2" /></div>

                        <div class="col-sm-3" id="p4r2c6"></div>

                    </div>

                    <div class="row" id="p4r3">
                        <div class="col-sm-3" id="p4r3c1"></div>
                        <div class="col-sm-6" id="p4r3c2">Please pick a Sunday or Wednesday</div>

                        <div class="col-sm-3" id="p4r3c4"></div>




                    </div>
                    <div class = "row" id="Vinton">
                        <div class="col-sm-5">
                            <iframe id="VintonMap" width="225" height="400"
                                    src="https://www.openstreetmap.org/export/embed.html?bbox=-92.03431606292726%2C42.15148139482261%2C-92.02015399932861%2C42.158639653889736&amp;layer=hot&amp;marker=42.155060625561326%2C-92.02723503112793"
                                    style="border: 1px solid black"></iframe><br/><small>
                            <a href="https://www.openstreetmap.org/?mlat=42.15506&amp;mlon=-92.02724#map=17/42.15506/-92.02724&amp;layers=H" target=”_blank” >View Larger Map</a></small></div>
                        <div class="col-sm-7">
                            <div class="row"><h2>Vinton Skate & Activity Center</h2></div>
                            <div class="row" id="Vaddress" ><p><strong>1703 C Ave,<br/>
                                Vinton, IA 52349</strong></p></div>
                            <div class="row "><p>The Vinton Parks & Recreation Department offers a vast selection of recreational and enrichment activities for residents of
                                all ages and abilities. The department also operates many facilities and amenities including the Recreation Center,
                                Skate & Activity Center, Miniature Golf Course, Vinton Community Swimming Pool, a splashpad, and many acres of parks, playgrounds and trails.</p></div>
                            <div class="row"><a href="http://www.vprdzone.com/skate-activity-center" target=”_blank”>Vinton Skate & Activity Center</a></div>


                        </div>


                    </div>
                    <div class = "row" id="GameOn">
                        <div class="col-sm-5"><iframe id="GameOnMap" width="225" height="400"
                                                      src="https://www.openstreetmap.org/export/embed.html?bbox=-91.71491861343385%2C42.05371411420431%2C-91.69700145721436%2C42.06088342027555&amp;layer=hot"
                                                      style="border: 1px solid black"></iframe><br/>
                            <small><a href="https://www.openstreetmap.org/#map=17/42.05730/-91.70596&amp;layers=H" target=”_blank” >View Larger Map</a></small></div>
                        <div class="col-sm-7">
                            <div class="row"><h2>GameOn Sports CR</h2></div>
                            <div class="row" id="Gaddress" ><p><strong>4655 Tower Terrace Rd,<br/>
                                Cedar Rapids, IA, 52411</strong></p></div>
                            <div class="row "><p>The vision at GameOn Sports is to establish a

                                destination that delivers world class athletic and event facilities
                                promoting community, competition, and connections.
                                We are thrilled to partner with GameOn for bouts and practices.</p></div>
                            <div class="row"><a href="https://gameonsportscr.com/"> Game On Sports</a></div>


                        </div>


                    </div>



                    <div class=row>
                        <div class="col-sm-3"></div>
                        <div class="col-sm-7">
                            <button type="button" value="Submit" id="tab3submit" ></button>
                        </div>
                        <div class="col-sm-2"></div>

                    </div>
                </form>
            </div>
            <div id="submit">
                test form 4

                <form id="testForm4">
                    <p>Please sign your name here.</p>
                    <input type="text" name="SignName" id="p4name"/><br />
                    <button type="button" value="Submit" id="tab4submit" ></button>
                </form>
            </div>
        </div>



        <div id="confirm">
            <h3>Are you ready?</h3>
            <p>By submitting this form, you are stating you are ready for fast paced action and skates thundering on the track </p>
        </div>
    <!-- end form-->



<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>
