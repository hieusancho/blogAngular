import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from 'src/app/service/comment.service';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.css']
})
export class ViewPostComponent {
  postId = this.activatedRoute.snapshot.params['id'];
  postData: any;
  comments: any;
  commentForm!: FormGroup;



  constructor(private postService: PostService, private activatedRoute: ActivatedRoute, private snackbar : MatSnackBar,
              private fb:FormBuilder,
              private commentService: CommentService
    ){

  }

  ngOnInit(){
    //lay ở dưới
    this.getPostById();

    this.commentForm = this.fb.group({
      postedBy: [null, Validators.required],
      content: [null, Validators.required]
    })
  }

  publishComment(){
    const postedBy = this.commentForm.get('postedBy')?.value;
    const content  = this.commentForm.get('content')?.value;
    console.log('postedBy:', postedBy);
    console.log('content:', content);


    this.commentService.createComment(this.postId, postedBy,content).subscribe(res =>{
      this.snackbar.open("Comment Published Successfully");
      this.getCommentsByPost();
    },error =>{
      this.snackbar.open("Something went wrong")
    }
    )
  }

  getCommentsByPost(){
    this.commentService.getAllCommentsByPost(this.postId).subscribe(res=>{
      this.comments = res;
    },error =>{
      this.snackbar.open("Something went wrong")
    })
  }



  getPostById(){
    this.postService.getPostById(this.postId).subscribe(res =>{
      this.postData = res;
      //lay comment
      this.getCommentsByPost();
    },error =>{
      this.snackbar.open("Something went wrong")
    })
  }

  likePost(){
    this.postService.likePost(this.postId).subscribe(res =>{
      this.snackbar.open("Post liked successfully" );
      this.getPostById();

    },error =>{
      this.snackbar.open("Something went wrong");
    })
  }

 

}
