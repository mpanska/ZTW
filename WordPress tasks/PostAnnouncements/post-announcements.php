<?php
/**
 * Plugin Name: Post Announcements
 * Plugin URI: https://example.com/plugins/Post Announcements/
 * Description: Add random announcment at the beginning of every post.
 * Version: 1.0
 * Requires at least: 5.0
 * Requires PHP: 7.2
 * Authors: Marharyta Panska, Kamil Nita
 * License: GPL v2 or later
 * License URI: https://www.gnu.org/licenses/gpl-2.0.html
 */
$announcements = array();
 
function rpa_admin_actions_register_menu(){
    add_options_page("Post Announcements", "Add Posts Announcements", 'manage_options', "rpa", "rpa_admin_page");
}

add_action('admin_menu', 'rpa_admin_actions_register_menu'); 


function rpa_admin_page(){
    //read current option value
	global $announcements;
	$announcements = get_option('announcements');
    global $_POST;
    // process changes from form
    if(isset($_POST['rpa_to_submit'])){
        if($_POST['rpa_to_submit'] == 'Y'){
            //setting a variable with the title and content
            $title = $_POST['pa_title'];
            $pa_content = $_POST['pa_content'];
            echo '<div class="notice notice-success isdismissible"><p>Submited.</p></div>';

            //put new announcement to an array and update this option
            array_push($announcements, array('title' => $title, 'content' => $pa_content));
            update_option('announcements', $announcements);			
        } 
    } elseif(isset($_POST['rpa_index'])){	
		echo '<div class="notice notice-success isdismissible"><p>Deleted</p></div>';		
		array_splice($announcements, $_POST['rpa_index'], 1);
		update_option('announcements', $announcements);
		
	} elseif(isset($_POST['rpa_to_edit'])){
        if($_POST['rpa_to_edit'] == 'Y'){
            $id = $_POST['pa_id'];
            $title = $_POST['pa_title'];
            $pa_content = $_POST['pa_content'];
            echo '<div class="notice notice-success isdismissible"><p>Edited</p></div>';

            $announcements[$id] = array('title' => $title, 'content' => $pa_content);
            update_option('announcements', $announcements);			
        } 
    }

    //display admin page
    ?>
    <h1>Admin page</h1>
    <div class="wrap">
        <!-- define the content of announcemet -->
        <form name="rpa_form" method="post">
            <input type="hidden" name="rpa_to_submit" value="Y">
            <p>Announcement title:</br> <input required type="text" name="pa_title" minlength="2" maxlength="20" value="<?php $title ?>"></p>
            <p>Content:</br> <input required type="text" name="pa_content" minlength="5" maxlength="125" value="<?php $pa_content ?>"></p>
            <p class="submit"><input type="submit" value="Submit"></p>
        </form>
    </div>

    <?php 
        //print out all the announcements
        if(!empty($announcements)){
            foreach($announcements as $index=>$item){ 
            ?>
			<form name="rpa_form" method="post">
				<hr>
                <h2><?php echo $item["title"]?></h2>
                <p><?php echo $item["content"]?></p>
                <input type="hidden" name="rpa_index" value=<?php echo $index?>>
                <div class="controls">
                    <a href="<?=admin_url("options-general.php?page=rpa-edit&id=".$index)?>">Edit</a>
                    <input id="delete-btn" type="submit" value="Delete">
                </div>
			</form>
            <?php 
            }
        }
        
}

//edit menu
function rpa_admin_actions_edit_menu(){
    add_submenu_page(null, "Post Announcements", "Edit Posts Announcements", 'manage_options', "rpa-edit", "rpa_edit_page");
}

add_action('admin_menu', 'rpa_admin_actions_edit_menu'); 


function rpa_edit_page(){
	global $announcements;
	$announcements = get_option('announcements');
	global $_POST;
    global $_GET;
	$id = $_GET['id'];
	$title = $announcements[$id]['title'];
	$content = $announcements[$id]['content'];
	
	?>
    <h1>Edit</h1>
    <div class="wrap">
        <!-- define the content of announcemet -->
        <form name="rpa_form" method="post" action="<?=admin_url("options-general.php?page=rpa")?>">
            <input type="hidden" name="rpa_to_edit" value="Y">
			<input type="hidden" name="pa_id" value=<?php echo $id?>>
            <p>Announcement title:</br> <input required type="text" name="pa_title" minlength="2" maxlength="20" value="<?php echo $title ?>"></p>
            <p>Content:</br> <input required type="text" name="pa_content" minlength="5" maxlength="125" value="<?php echo $content ?>"></p> 
            <p class="submit"><input type="submit" value="Edit"></p>
        </form>
    </div>
	<a href="<?=admin_url("options-general.php?page=rpa")?>">Back</a>
    <?php 
}


function add_before_content($content) {
    global $announcements;
    $announcements = get_option('announcements');
    $index = array_rand($announcements);
    $item = $announcements[$index];
    
    return '<div class="announcement"><h5>'.$item['title'].'</h5><p>'.$item['content'].'</p></div><br>'.$content;
}

add_filter('the_content', 'add_before_content');


function rpa_add_styles(){
    //register style
    wp_register_style('rpa_styles', plugins_url('/css/style.css', __FILE__));
    //enable style (load in meta of html)
    wp_enqueue_style('rpa_styles');
}

add_action('init', 'rpa_add_styles'); 